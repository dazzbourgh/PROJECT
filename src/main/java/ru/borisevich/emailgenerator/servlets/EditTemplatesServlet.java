package ru.borisevich.emailgenerator.servlets;

import org.apache.log4j.Logger;
import ru.borisevich.emailgenerator.db.TemplateDAO;
import ru.borisevich.emailgenerator.listeners.DbInitListener;
import ru.borisevich.emailgenerator.model.Template;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Leonid on 26.09.2016.
 */
@WebServlet("/editTemplates")
public class EditTemplatesServlet extends AbstractTemplateServlet {
    public static final Logger LOGGER = Logger.getLogger(EditAddressesServlet.class.getName());

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/editTemplates").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        TemplateDAO templateDAO = (TemplateDAO) req.getServletContext().getAttribute(DbInitListener.TEMPLATE_DAO);
        int i = Integer.parseInt(req.getParameter("templateQuantity").toString());
        for (int j = 0; j < i; j++) {
            if (!templateDAO.updateTemplate(new Template(
                    Integer.parseInt(req.getParameter("template_id" + j)),
                    req.getParameter("text" + j)
            ))) {
                LOGGER.error("Could not update template");
            }
        }
        req.getRequestDispatcher("/editTemplatesLoader").forward(req, resp);
    }
}
