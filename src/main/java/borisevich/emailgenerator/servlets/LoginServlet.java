package borisevich.emailgenerator.servlets;

import borisevich.emailgenerator.db.DBConnector;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.ResultSet;

/**
 * Created by Leonid on 24.08.2016.
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    boolean checkAuth(HttpServletRequest req){
        try {
            ResultSet rs = new DBConnector().executeStatement("SELECT * " +
                    "FROM USERS " +
                    "WHERE username=\'" +
                    req.getParameter("username") +
                    "\' & password=\'" +
                    req.getParameter("password") +
                    "\';"
            );
            if (rs != null) {
                req.getSession().setAttribute("isLoggedIn", "true");
                return true;
            }
        } catch (NullPointerException e){
            return false;
        }
        return false;
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(checkAuth(req)){
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("/generation");
            requestDispatcher.forward(req, resp);
        } else{
            req.getRequestDispatcher("/login").forward(req, resp);
        }
    }
}
