package borisevich.emailgenerator.servlets;

import borisevich.emailgenerator.db.UserDAO;
import borisevich.emailgenerator.functional.AuthTokenContainer;
import borisevich.emailgenerator.model.User;
import borisevich.emailgenerator.listeners.DbInitListener;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

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

    boolean checkUserCorrect(HttpServletRequest req) {
        UserDAO userDAO = (UserDAO)req.getServletContext().getAttribute(DbInitListener.USER_DAO);

        logger.debug("Checking user correctness");
        Object userToken = req.getSession().getAttribute("token");
        if (userToken == null) {
            logger.debug("Token is null, checking user...");
            if (userDAO.checkPassword(new User(req.getParameter("username"), req.getParameter("password")))) {
                String token = AuthTokenContainer.getInstance().generateToken();
                AuthTokenContainer.getInstance().addToken(token);
                req.getSession().setAttribute("token", token);
                req.getSession().setAttribute("user_id", userDAO.getUserId(req.getParameter("username")));
                logger.debug("User logged in with token: " + token);
                return true;
            }
        }else{
            if(AuthTokenContainer.getInstance().containsToken(userToken.toString())){
                return true;
            }
        }
        logger.info("User not found");
        return false;
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (checkUserCorrect(req)) {
            req.getRequestDispatcher("/menu.jsp").forward(req, resp);
        } else {
            req.getRequestDispatcher("/login.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/login.jsp").forward(req, resp);
    }
}
