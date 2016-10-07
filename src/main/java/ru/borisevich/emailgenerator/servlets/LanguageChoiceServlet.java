package ru.borisevich.emailgenerator.servlets;

import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Leonid on 17.09.2016.
 */

/**
 * Sends redirect after language choice to pass the request over
 * to filter so it can avoid login page if user is already logged in.
 */

@WebServlet("/languageChoice")
public class LanguageChoiceServlet extends HttpServlet {
    private static Logger LOGGER = Logger.getLogger(LanguageChoiceServlet.class.getName());
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getSession().setAttribute("language", req.getParameter("language"));
        resp.sendRedirect("/ru.borisevich.emailgenerator/login.jsp");
    }
}
