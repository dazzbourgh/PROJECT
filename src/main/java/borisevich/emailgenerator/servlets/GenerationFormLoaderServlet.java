package borisevich.emailgenerator.servlets;

import borisevich.emailgenerator.db.DBConnector;
import borisevich.emailgenerator.db.MySQLAddressDAO;
import org.apache.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Leonid on 25.08.2016.
 */
@WebServlet("/generationFormLoader")
public class GenerationFormLoaderServlet extends HttpServlet {
    final Logger logger = Logger.getLogger(GenerationFormLoaderServlet.class.getName());

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("labelsList", new MySQLAddressDAO().findAll());
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/generation.jsp");
        requestDispatcher.forward(req, resp);
    }
}
