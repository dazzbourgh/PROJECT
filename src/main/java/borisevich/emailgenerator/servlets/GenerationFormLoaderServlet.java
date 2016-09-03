package borisevich.emailgenerator.servlets;

import borisevich.emailgenerator.db.DBConnector;
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

    //TODO: add db support
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try (DBConnector dbConnector = new DBConnector()){
            ResultSet rs = dbConnector.executeStatement("SELECT * FROM addresses");
            List<String> labelsList = new ArrayList<String>();
            while(rs.next()){
                labelsList.add(rs.getString("address"));
            }
            rs.close();
            req.setAttribute("labelsList", labelsList);
        } catch (SQLException e) {
            logger.error("Error while retrieving labels list from DB");
        }
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/generation.jsp");
        requestDispatcher.forward(req, resp);
    }
}
