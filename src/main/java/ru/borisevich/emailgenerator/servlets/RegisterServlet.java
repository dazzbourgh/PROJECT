package ru.borisevich.emailgenerator.servlets;

import ru.borisevich.emailgenerator.db.UserDAO;
import ru.borisevich.emailgenerator.model.User;
import ru.borisevich.emailgenerator.listeners.DbInitListener;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;
import java.util.regex.PatternSyntaxException;

/**
 * Created by Leonid on 02.09.2016.
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
        try {
            if (!req.getParameter("username").matches("[a-zA-Z][a-zA-Z0-9]{1,14}"))
                return true;
        }catch (PatternSyntaxException e){
            LOGGER.error("Incorrect regular expression");
        }
        return false;
    }
    private boolean checkUserAlreadyExists(HttpServletRequest req){
        UserDAO userDAO = (UserDAO)req.getServletContext().getAttribute(DbInitListener.USER_DAO);
        if(userDAO.findByName(req.getParameter("username")).equals(Collections.emptyList()))
            return false;
        return true;
    }
    private boolean checkPasswordsNotMatch(HttpServletRequest req){
        if(req.getParameter("password").equals(req.getParameter("password2"))){
            return false;
        }
        return true;
    }
    private void registerUser(HttpServletRequest req, String username, String password){
        UserDAO userDAO = (UserDAO)req.getServletContext().getAttribute(DbInitListener.USER_DAO);
        userDAO.insertUser(new User(username, password));
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(checkUsernameNull(req)) {
            req.setAttribute("Error", "Please, enter username.");
            req.getRequestDispatcher("/register.jsp").forward(req, resp);
            return;
        }
        if(checkUsernameIncorrect(req)) {
            req.setAttribute("Error", "Only letters and numbers are allowed for username, at least 2 symbols.");
            req.getRequestDispatcher("/register.jsp").forward(req, resp);
            return;
        }
        if(checkPasswordNull(req)) {
            req.setAttribute("Error", "Please, enter password.");
            req.getRequestDispatcher("/register.jsp").forward(req, resp);
            return;
        }
        if(checkPasswordsNotMatch(req)) {
            req.setAttribute("Error", "Passwords do not match.");
            req.getRequestDispatcher("/register.jsp").forward(req, resp);
            return;
        }
        if(checkUserAlreadyExists(req)) {
            req.setAttribute("Error", "This user already exists. Please, choose another username.");
            req.getRequestDispatcher("/register.jsp").forward(req, resp);
            return;
        }
        registerUser(req, req.getParameter("username"), req.getParameter("password"));
        req.getRequestDispatcher("/login.jsp").forward(req, resp);
    }
}
