package ru.borisevich.emailgenerator.servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Leonid on 27.09.2016.
 */
@WebServlet("/headerMenu")
public class HeaderMenuServlet extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getParameter("myAccountButton") != null){
            req.getRequestDispatcher("/myaccount").forward(req, resp);
            return;
        } else if(req.getParameter("generationButton") != null){
            req.getRequestDispatcher("/generationFormLoader").forward(req, resp);
            return;
        } else if(req.getParameter("editAddressesButton") != null){
            req.getRequestDispatcher("/editAddressesLoader").forward(req, resp);
            return;
        } else if(req.getParameter("editTemplatesButton") != null){
            req.getRequestDispatcher("/editTemplatesLoader").forward(req, resp);
            return;
        }
    }
}
