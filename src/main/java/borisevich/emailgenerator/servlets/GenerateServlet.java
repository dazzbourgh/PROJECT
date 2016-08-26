package borisevich.emailgenerator.servlets;

import borisevich.emailgenerator.db.DBConnector;
import borisevich.emailgenerator.functional.Generator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by Leonid on 26.08.2016.
 */
public class GenerateServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String trackInfoString = req.getParameter("trackInfo");
        String[] adresses = new String[5];
        DBConnector dbConnector = new DBConnector();
        Generator generator = new Generator();

        generator.generateMails(adresses, trackInfoString);
    }
}
