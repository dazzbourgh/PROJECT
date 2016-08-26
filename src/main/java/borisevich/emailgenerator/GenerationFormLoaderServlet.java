package borisevich.emailgenerator;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Leonid on 25.08.2016.
 */
@WebServlet("/generation")
public class GenerationFormLoaderServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String[] labels = new String[5];
        List<String> labelsList = new ArrayList<String>(5);

        for(int i = 0; i < labels.length; i++) {
            labels[i] = "label " + i;
            labelsList.add(labels[i]);
        }
        req.setAttribute("labelsList", labelsList);
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/generation.jsp");
        requestDispatcher.forward(req, resp);
    }
}
