package borisevich.emailgenerator.servlets;

import borisevich.emailgenerator.db.AddressDAO;
import borisevich.emailgenerator.model.Address;
import borisevich.emailgenerator.listeners.DbInitListener;
import org.apache.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by Leonid on 25.08.2016.
 */
@WebServlet("/generationFormLoader")
public class GenerationFormLoaderServlet extends HttpServlet {
    final Logger LOGGER = Logger.getLogger(GenerationFormLoaderServlet.class.getName());

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        AddressDAO addressDAO = (AddressDAO)req.getServletContext().getAttribute(DbInitListener.ADDRESS_DAO);
        List<Address> addressList = addressDAO.findAll();
        req.setAttribute("addressList", addressList);
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/generation.jsp");
        requestDispatcher.forward(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }
}
