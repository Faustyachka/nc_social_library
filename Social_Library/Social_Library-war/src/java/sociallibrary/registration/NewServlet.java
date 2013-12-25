package sociallibrary.registration;

import java.io.IOException;
import java.io.PrintWriter;
import java.rmi.ServerException;
import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.CreateException;
import javax.ejb.FinderException;
import javax.mail.Message.RecipientType;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import sociallibrary.*;
import sociallibrary.dao.Dao;
import sociallibrary.entity.Role;
import sociallibrary.entity.Users;


/**
 *
 * @author Felix
 */
public class NewServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        Users users = new Users();
        users.setId(18);
        users.setFirsName(request.getParameter("firsName"));
        users.setLastName(request.getParameter("lastName"));
        users.setEmail(request.getParameter("email"));
        users.setLogin(request.getParameter("login"));
        users.setPassword(request.getParameter("password"));
        users.setGender(1);
        users.setConfirmed(0);
        users.setBanned(1);
        users.setRegistrationData("2013/12/25");
        users.setNotify(1);
        users.setRole(1);
            
        Dao dao = new Dao();
        dao.createUsers(users);

        String mailSub = "Registration on Social Library";
        String mailText = "Please copy and use link: 'http://localhost:8080/Social_Library-war/ConfirmedRegistration?users="+users.getLogin()+"'";
        try {
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Configyration</title>");
            out.println("</head>");
            out.println("You are registration! Please complite registration on you mail!");
            out.println("<body>");           
            out.println("</body>");
            out.println("</html>");
            sendMail(users.getEmail(), mailSub, mailText);

        } catch (NamingException e) {
            throw new ServerException("Naming error", e);
        } catch (MessagingException e) {
            throw new ServerException("Sending error", e);
        }
        finally {
            out.close();
        }
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        processRequest(request, response);
    }

    private Session getMailSession() throws NamingException {
        Context c = new InitialContext();
        return (Session) c.lookup("java:comp/env/mailSession");
    }

    private void sendMail(String email, String subject, String body) throws NamingException, MessagingException {
        Session mailSession = getMailSession();
        MimeMessage message = new MimeMessage(mailSession);
        message.setSubject(subject);
        message.setRecipients(RecipientType.TO, InternetAddress.parse(email, false));
        message.setText(body);
        Transport.send(message);
    }
}


