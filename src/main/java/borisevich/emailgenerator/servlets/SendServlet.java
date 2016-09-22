package borisevich.emailgenerator.servlets;

import borisevich.emailgenerator.db.EmailDAO;
import borisevich.emailgenerator.listeners.DbInitListener;
import borisevich.emailgenerator.model.Email;
import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by Leonid on 15.09.2016.
 */
@WebServlet("/sendServlet")
public class SendServlet extends HttpServlet{
    private static final Logger LOGGER = Logger.getLogger(SendServlet.class.getName());

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        EmailDAO emailDAO = (EmailDAO)req.getServletContext().getAttribute(DbInitListener.EMAIL_DAO);

        if(req.getSession().getAttribute("emailList") == null){
            req.setAttribute("Error", "Error: emails were not generated");
            req.getRequestDispatcher("/generationFormLoader").forward(req, resp);
            return;
        }
        if(req.getParameter("emailUsername") == null){
            req.setAttribute("Error", "Enter username");
            req.getRequestDispatcher("/generationFormLoader").forward(req, resp);
            return;
        }
        if(req.getParameter("emailPassword") == null){
            req.setAttribute("Error", "Enter password");
            req.getRequestDispatcher("/generationFormLoader").forward(req, resp);
            return;
        }
        List<Email> emailList = (List<Email>)req.getSession().getAttribute("emailList");

        String username = req.getParameter("emailUsername");
        String password = req.getParameter("emailPassword");
        org.apache.commons.mail.Email email = new SimpleEmail();
        email.setHostName("smtp.gmail.com");
        email.setSmtpPort(587);
        email.setSSL(true);
        email.setAuthenticator(new DefaultAuthenticator(username, password));

        try {
            email.setFrom(username + "@gmail.com");
            email.setSubject("TestMail");
            for (Email e : emailList) {
                email.setMsg(e.getText());
                email.addTo(e.getAddress());
                email.send();
                emailDAO.insertEmail(e, Integer.parseInt(req.getSession().getAttribute("user_id").toString()));
            }
        } catch(EmailException e){
            LOGGER.error("Error while sending email:");
            LOGGER.error(e);
            req.setAttribute("Error", "Error: message can not be sent");
        }
        req.getRequestDispatcher("/generationFormLoader").forward(req, resp);
    }
}
