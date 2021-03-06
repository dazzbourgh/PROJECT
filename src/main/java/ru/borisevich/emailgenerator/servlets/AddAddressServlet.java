package ru.borisevich.emailgenerator.servlets;

import ru.borisevich.emailgenerator.db.AddressDAO;
import ru.borisevich.emailgenerator.model.Address;
import ru.borisevich.emailgenerator.listeners.DbInitListener;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Leonid on 11.09.2016.
 */
@WebServlet("/addAddress")
public class AddAddressServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        AddressDAO addressDAO = (AddressDAO)req.getServletContext().getAttribute(DbInitListener.ADDRESS_DAO);
        addressDAO.insertAddress(
                new Address(
                        0,
                        req.getParameter("address"),
                        req.getParameter("name")
                )
        );
        req.getRequestDispatcher("/editAddressesLoader").forward(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/editAddressesLoader").forward(req, resp);
    }
}
