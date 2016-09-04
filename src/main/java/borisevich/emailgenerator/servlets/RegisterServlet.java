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
 * Created by Leonid on 02.09.2016.
 */
/*TODO: add checking of
    -null username
    -null password
    -incorrect username
    -incorrect password
    -passwords do not match
    -user already exists
 */


@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
    static final Logger LOGGER = Logger.getLogger(RegisterServlet.class.getName());
    private boolean checkUsernameNull(HttpServletRequest req){
        if(req.getParameter("username") == "")
            return true;
        return false;
    }
    private boolean checkPasswordNull(HttpServletRequest req){
        if(req.getParameter("password") == "" || req.getParameter("password2") == "")
            return true;
        return false;
    }
    private boolean checkUsernameIncorrect(HttpServletRequest req){
        //TODO: correct regex
        //if(!req.getParameter("username").matches("\\b[a-zA-Z][a-zA-Z0-9\\-._]{15,}\\b"))
        //    return true;
        return false;
    }
    private boolean checkUserAlreadyExists(HttpServletRequest req){
        try (DBConnector dbConnector = new DBConnector();){
            ResultSet resultSet = dbConnector.executeStatement(
                    "SELECT * FROM USERS " +
                            "WHERE username=" +
                            req.getParameter("username")
            + ";");
            if(resultSet.next() == false)
                return true;
        } catch (SQLException e) {
            LOGGER.error("SQLException: can't check user's existence");
        }
        return false;
    }
    private boolean checkPasswordsNotMatch(HttpServletRequest req){
        if(req.getParameter("password").equals(req.getParameter("password2"))){
            return false;
        }
        return true;
    }
    private void registerUser(String username, String password){
        //TODO: add registration in DB (with hashCode)

        try (DBConnector dbConnector = new DBConnector()){
            dbConnector.executeUpdate(
                    "INSERT INTO users (username, password)" +
                            "VALUES (\'" + username + "\', \'" +
                            password + "\');");
        } catch (SQLException e) {
            LOGGER.error("SQLException: can't check user's existence");
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(checkUsernameNull(req)) {
            req.setAttribute("Error", "Please, enter username.");
            req.getRequestDispatcher("/register.jsp").forward(req, resp);
        }
        if(checkPasswordNull(req)) {
            req.setAttribute("Error", "Please, enter password.");
            req.getRequestDispatcher("/register.jsp").forward(req, resp);
        }
        if(checkUsernameIncorrect(req)) {
            req.setAttribute("Error", "Only letters and numbers are allowed for username.");
            req.getRequestDispatcher("/register.jsp").forward(req, resp);
        }
        if(checkPasswordsNotMatch(req)) {
            req.setAttribute("Error", "Passwords do not match.");
            req.getRequestDispatcher("/register.jsp").forward(req, resp);
        }
        if(checkUserAlreadyExists(req)) {
            req.setAttribute("Error", "This user already exists. Please, choose another username.");
            req.getRequestDispatcher("/register.jsp").forward(req, resp);
        }
        registerUser(req.getParameter("username"), req.getParameter("password"));
        req.getRequestDispatcher("/login.jsp").forward(req, resp);
    }
}
