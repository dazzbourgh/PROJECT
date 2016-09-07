package borisevich.emailgenerator.servlets;

import borisevich.emailgenerator.db.DBConnector;
import borisevich.emailgenerator.db.MySQLUserDAO;
import borisevich.emailgenerator.functional.AuthTokenContainer;
import borisevich.emailgenerator.functional.User;
import org.apache.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Leonid on 24.08.2016.
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    Logger logger;
    @Override
    public void init() throws ServletException {
        super.init();
        logger = Logger.getLogger(LoginServlet.class.getName());
    }

    boolean checkUserCorrect(HttpServletRequest req){
        logger.debug("Checking user correctness");
        if(req.getSession().getAttribute("token")==null){
            logger.debug("Token is null, checking user...");
            if(new MySQLUserDAO().checkPassword(new User(req.getParameter("username"), req.getParameter("password")))){
                String token = AuthTokenContainer.getInstance().generateToken();
                AuthTokenContainer.getInstance().addToken(token);
                req.getSession().setAttribute("token", token);
                req.getSession().setAttribute("user_id", new MySQLUserDAO().getUserId(req.getParameter("username")));
                logger.debug("User logged in with token: " + token);
                return true;
            }
        }
        logger.info("User not ");
        return false;
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(checkUserCorrect(req)){
            req.getRequestDispatcher("/menu.jsp").forward(req, resp);
        } else{
            req.getRequestDispatcher("/index.jsp").forward(req, resp);
        }
    }
}
