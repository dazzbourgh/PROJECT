package borisevich.emailgenerator.servlets;

import borisevich.emailgenerator.db.AddressDAO;
import borisevich.emailgenerator.db.MySQL.MySQLAddressDAO;
import borisevich.emailgenerator.functional.Address;
import borisevich.emailgenerator.listeners.DbInitListener;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Leonid on 08.09.2016.
 */
@WebServlet("/editAddresses")
public class EditAddressesServlet extends HttpServlet {
    public static final Logger LOGGER = Logger.getLogger(EditAddressesServlet.class.getName());
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        AddressDAO addressDAO = (AddressDAO)req.getServletContext().getAttribute(DbInitListener.ADDRESS_DAO);
        int i = Integer.parseInt(req.getParameter("quantity").toString());
        for(int j = 0; j < i; j++){
            LOGGER.debug(req.getParameter("address_id" + j));
            if(!addressDAO.updateAddress(new Address(
                    Integer.parseInt(req.getParameter("address_id" + j).toString()),
                    req.getParameter("address" + j).toString(),
                    req.getParameter("name" + j).toString()
            ))){
                LOGGER.error("Could not update address");
            }
        }
        req.getRequestDispatcher("/editAddressesLoader").forward(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/editAddressesLoader").forward(req, resp);
    }
}
