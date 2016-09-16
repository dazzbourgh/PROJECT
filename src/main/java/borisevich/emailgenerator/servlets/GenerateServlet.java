package borisevich.emailgenerator.servlets;

import borisevich.emailgenerator.db.DBConnector;
import borisevich.emailgenerator.db.MySQLAddressDAO;
import borisevich.emailgenerator.functional.Address;
import borisevich.emailgenerator.functional.Email;
import borisevich.emailgenerator.functional.Generator;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Leonid on 26.08.2016.
 */
@WebServlet("/generateServlet")
public class GenerateServlet extends HttpServlet {
    private static final Logger LOGGER = Logger.getLogger(GenerateServlet.class.getName());
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Email> emailList;
        List<Address> addressList = new ArrayList<>();
        String trackInfoString = req.getParameter("trackInfo");
        String[] addressNames = req.getParameterValues("address");
        for(String s : addressNames){
            LOGGER.debug("Address to find: " + s);
            addressList.add(new MySQLAddressDAO().findByName(s));
        }
        Address[] addresses = new Address[addressList.size()];
        addressList.toArray(addresses);
        LOGGER.debug("Address 0: " + addresses[0]);
        Generator generator = new Generator();
        try {
            emailList = generator.generateMails(addresses, trackInfoString);
            req.getSession().setAttribute("emailList", emailList);
            LOGGER.debug("FIRST EMAIL: " + emailList.get(0).getText());
        } catch (NullPointerException e){
            LOGGER.error("Error during generation: null pointer exception");
        }
        req.getRequestDispatcher("/generationFormLoader").forward(req, resp);
    }
}
