package sociallibrary.register;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.CreateException;
import javax.ejb.EJBException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import socaillibrary.registration.RegistrationSessionBeanLocal;
import socaillibrary.registration.RegistrationSessionBeanLocalHome;

import sociallibrary.entity.*;


public class RegisterServlet extends HttpServlet {
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, EJBException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        Users users = new Users();
        users.setId(3);
        users.setFirstName(request.getParameter("firstName"));
        users.setLastName(request.getParameter("lastName"));
        users.setEmail(request.getParameter("email"));
        users.setLogin(request.getParameter("login"));
        users.setPassword(request.getParameter("password"));
        users.setGender(1);
        users.setConfirmed(0);
        users.setBanned(0);
        users.setRegistrationDate("11.02.2013");
        users.setNotify(1);
        users.setRole(1);
        
        
               
        try {
            
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet RegisterServlet</title>");  
            out.println("</head>");
            RegistrationSessionBeanLocal reg = this.lookupRegistrationSessionBeanLocal();
            out.println("<body>");
            out.println("test1");
            reg.setRegistration(users);
            out.println("test2");
            out.println("</body>");
            out.println("</html>");
             
            
        }  finally {            
            out.close();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
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

    /** 
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    private RegistrationSessionBeanLocal lookupRegistrationSessionBeanLocal() {
        try {
            Context c = new InitialContext();
            RegistrationSessionBeanLocalHome rv = (RegistrationSessionBeanLocalHome) c.lookup("java:comp/env/RegistrationSessionBean");
            return rv.create();
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        } catch (CreateException ce) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ce);
            throw new RuntimeException(ce);
        }
    }
}
