package borisevich.emailgenerator.servlets;

import borisevich.emailgenerator.db.AddressDAO;
import borisevich.emailgenerator.db.MySQL.MySQLAddressDAO;
import borisevich.emailgenerator.listeners.DbInitListener;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Leonid on 08.09.2016.
 */
@WebServlet("/editAddressesLoader")
public class EditAddressesLoaderServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        AddressDAO addressDAO = (AddressDAO)req.getServletContext().getAttribute(DbInitListener.ADDRESS_DAO);
        req.setAttribute("addressList", addressDAO.findAll());
        req.getRequestDispatcher("/edit_addresses.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
