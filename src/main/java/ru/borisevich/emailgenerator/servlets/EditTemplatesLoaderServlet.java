package ru.borisevich.emailgenerator.servlets;

import org.apache.log4j.Logger;
import ru.borisevich.emailgenerator.db.TemplateDAO;
import ru.borisevich.emailgenerator.listeners.DbInitListener;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Leonid on 26.09.2016.
 */
@WebServlet("/editTemplatesLoader")
public class EditTemplatesLoaderServlet extends AbstractTemplateServlet {
    public static final Logger LOGGER = Logger.getLogger(EditTemplatesLoaderServlet.class.getName());
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        TemplateDAO templateDAO = (TemplateDAO) req.getServletContext().getAttribute(DbInitListener.TEMPLATE_DAO);
        req.setAttribute("templatesList", templateDAO.getAll());
        req.getRequestDispatcher("/edit_templates.jsp").forward(req,resp);
        LOGGER.debug("Template id: " + templateDAO.getAll().get(0).getTemplate_id());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
