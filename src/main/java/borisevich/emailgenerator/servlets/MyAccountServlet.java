package borisevich.emailgenerator.servlets;

import borisevich.emailgenerator.db.MySQLAddressDAO;
import borisevich.emailgenerator.db.MySQLEmailDAO;
import borisevich.emailgenerator.functional.Email;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by Leonid on 06.09.2016.
 */
@WebServlet("/myaccount")
public class MyAccountServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Email> emailList = new MySQLEmailDAO().findByUserId(0);
        req.setAttribute("emailList", emailList);
        req.getRequestDispatcher("/myaccount.jsp").forward(req, resp);
    }
}
