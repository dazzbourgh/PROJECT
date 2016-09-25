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
@WebServlet("/languageChoice")
public class LanguageChoiceServlet extends HttpServlet {
    private static Logger LOGGER = Logger.getLogger(LanguageChoiceServlet.class.getName());
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getSession().setAttribute("language", req.getParameter("language"));
        req.getRequestDispatcher("/login.jsp").forward(req, resp);
    }
}
