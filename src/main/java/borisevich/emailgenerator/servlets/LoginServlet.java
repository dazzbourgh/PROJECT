package borisevich.emailgenerator.servlets;

import borisevich.emailgenerator.db.DBConnector;
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

    //TODO: fix checkAuth
    boolean checkAuth(HttpServletRequest req){
        logger.debug("Checking auth");
        try {
            DBConnector dbConnector = new DBConnector();
            ResultSet rs = dbConnector.executeStatement("SELECT * " +
                    "FROM USERS " +
                    "WHERE username=\'" + req.getParameter("username") +
                    "\' && password=\'" + req.getParameter("password") +
                    "\';"
            );

            rs.next();
            if (rs.getString("username") != null) {
                req.getSession().setAttribute("isLoggedIn", "true");
                logger.debug("User logged: " + rs.getString("username"));
                dbConnector.close();
                return true;
            }
            dbConnector.close();
        } catch (NullPointerException e){
            logger.debug("ERROR: NullPointerException");
            return false;
        } catch (SQLException e){
            logger.debug("ERROR: SQLException");
            return false;
        }
        logger.debug("User not found");
        return false;
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(checkAuth(req)){
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("/generationFormLoader");
            requestDispatcher.forward(req, resp);
        } else{
            req.getRequestDispatcher("/index.jsp").forward(req, resp);
        }
    }
}
