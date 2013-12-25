package sociallibrary.registration;

import java.io.IOException;
import java.io.PrintWriter;
import java.rmi.ServerException;
import java.util.Collection;
import java.util.Properties;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.CreateException;
import javax.ejb.FinderException;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.Message.RecipientType;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
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
public class RegistrationServlet extends HttpServlet {

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
        //полная лажа, пока нету бд с секвинсами
        Random generator = new Random();
        int rand = generator.nextInt(1000000)+50;
        users.setId(rand);
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

    private void sendMail(String email, String subject, String body) throws NamingException, MessagingException {
        Properties mailProps = new Properties();
        mailProps.put("mail.smtp.host", "smtp.gmail.com");
        mailProps.put("mail.smtp.auth", "true");
        mailProps.put("mail.smtp.port", "465");
        mailProps.put("mail.smtp.socketFactory.port", "465");
        mailProps.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        mailProps.put("mail.smtp.socketFactory.fallback", "false");
        Session mailSession = Session.getInstance(mailProps, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return (new PasswordAuthentication("its.ti.02", "starosta"));
            }
        });
        
        MimeMessage message = new MimeMessage(mailSession);
        message.setFrom(new InternetAddress("its.ti.02@gmail.com"));
        String[] emails = {email}; //адреса получателей
        InternetAddress dests[] = new InternetAddress[emails.length];
        for (int i = 0; i < emails.length; i++) {
            dests[i] = new InternetAddress(emails[i].trim().toLowerCase());
        }
        message.setRecipients(Message.RecipientType.TO, dests);
        message.setSubject(subject, "KOI8-R");
        Multipart mp = new MimeMultipart();
        MimeBodyPart mbp1 = new MimeBodyPart();
        mbp1.setText(body, "KOI8-R");
        mp.addBodyPart(mbp1);
        message.setContent(mp);
        message.setSentDate(new java.util.Date());
        Transport.send(message);
    }
}


