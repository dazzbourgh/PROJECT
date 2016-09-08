package borisevich.emailgenerator.servlets;

import borisevich.emailgenerator.db.MySQLAddressDAO;
import borisevich.emailgenerator.functional.Address;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Leonid on 08.09.2016.
 */
public class EditAddressesServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int i = Integer.parseInt(req.getAttribute("quantity").toString());
        for(int j = 0; j < i; j++){
            new MySQLAddressDAO().updateAddress(new Address(
                    Integer.parseInt(req.getAttribute("address_id").toString()),
                    req.getAttribute("address").toString(),
                    req.getAttribute("name").toString()
            ));
        }
        req.getRequestDispatcher("/edit_addresses.jsp").forward(req, resp);
    }
}
