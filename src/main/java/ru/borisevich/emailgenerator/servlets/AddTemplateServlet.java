package ru.borisevich.emailgenerator.servlets;

import ru.borisevich.emailgenerator.db.TemplateDAO;
import ru.borisevich.emailgenerator.listeners.DbInitListener;
import ru.borisevich.emailgenerator.model.Template;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.oracle.jrockit.jfr.ContentType.Address;

/**
 * Created by Leonid on 26.09.2016.
 */
@WebServlet("/addTemplate")
public class AddTemplateServlet extends AbstractTemplateServlet{

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(req.getAttribute("text") == null){
            req.setAttribute("templateError", "Enter template text");
            req.getRequestDispatcher("/editTemplatesLoader").forward(req, resp);
            return;
        }
        String text = req.getAttribute("text").toString();
        if(!this.checkTemplateCorrect(text)){
            req.setAttribute("templateError", "Template missing keywords");
            req.getRequestDispatcher("/editTemplatesLoader").forward(req, resp);
            return;
        }
        TemplateDAO templateDAO = (TemplateDAO) req.getServletContext().getAttribute(DbInitListener.ADDRESS_DAO);
        templateDAO.insertTemplate(new Template(0, text));
        req.getRequestDispatcher("/editTemplatesLoader").forward(req, resp);
    }
}
