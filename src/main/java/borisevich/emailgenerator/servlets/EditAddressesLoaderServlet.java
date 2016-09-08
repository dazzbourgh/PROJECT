package borisevich.emailgenerator.servlets;

import borisevich.emailgenerator.db.DBConnector;
import borisevich.emailgenerator.db.MySQLAddressDAO;
import borisevich.emailgenerator.functional.Address;

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
        req.setAttribute("addressList", new MySQLAddressDAO().findAll());
        req.getRequestDispatcher("/edit_addresses.jsp").forward(req, resp);
    }
}
