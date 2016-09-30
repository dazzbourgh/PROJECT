package ru.borisevich.emailgenerator.servlets;

import ru.borisevich.emailgenerator.db.AddressDAO;
import ru.borisevich.emailgenerator.model.Address;
import ru.borisevich.emailgenerator.model.Email;
import ru.borisevich.emailgenerator.functional.Generator;
import ru.borisevich.emailgenerator.listeners.DbInitListener;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Leonid on 26.08.2016.
 */
@WebServlet("/generateServlet")
public class GenerateServlet extends HttpServlet {
    private static final Logger LOGGER = Logger.getLogger(GenerateServlet.class.getName());

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        AddressDAO addressDAO = (AddressDAO) req.getServletContext().getAttribute(DbInitListener.ADDRESS_DAO);
        if (req.getParameter("author").toString().equals("") ||
                req.getParameter("title").toString().equals("") ||
                req.getParameter("style").toString().equals("") ||
                req.getParameter("bpm").toString().equals("") ||
                req.getParameter("link").toString().equals("") ||
                req.getParameter("name").toString().equals("") ||
                req.getParameter("trackInfo").toString().equals("")) {
            req.setAttribute("Error", "Please, fill all fields.");
            req.getRequestDispatcher("/generationFormLoader").forward(req, resp);
            return;
        }
        if (req.getParameterValues("address") == null) {
            req.setAttribute("Error", "Please, choose recipients.");
            req.getRequestDispatcher("/generationFormLoader").forward(req, resp);
            return;
        }
        List<Email> emailList;
        List<Address> addressList = new ArrayList<>();
        String[] addressNames = req.getParameterValues("address");

        for (String s : addressNames) {
            LOGGER.debug("Address to find: " + s);
            addressList.add(addressDAO.findByName(s));
        }
        Address[] addresses = new Address[addressList.size()];
        addressList.toArray(addresses);
        LOGGER.debug("Address 0: " + addresses[0].getAddress());
        Generator generator = new Generator();
        try {
            emailList = generator.generateMails(addresses, req);
            req.getSession().setAttribute("emailList", emailList);
            LOGGER.debug("FIRST EMAIL: " + emailList.get(0).getText());
        } catch (IllegalArgumentException e) {
            LOGGER.error("Error during generation: illegal template");
        }
        req.getRequestDispatcher("/generationFormLoader").forward(req, resp);
    }
}
