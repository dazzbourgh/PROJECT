package ru.borisevich.emailgenerator.listeners;

import ru.borisevich.emailgenerator.db.mysql.MySQLAddressDAO;
import ru.borisevich.emailgenerator.db.mysql.MySQLEmailDAO;
import ru.borisevich.emailgenerator.db.mysql.MySQLTemplateDAO;
import ru.borisevich.emailgenerator.db.mysql.MySQLUserDAO;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * Created by Leonid on 22.09.2016.
 */
@WebListener
public class DbInitListener implements ServletContextListener {

    public static final String ADDRESS_DAO = "Address Dao";
    public static final String EMAIL_DAO = "Email Dao";
    public static final String TEMPLATE_DAO = "Template Dao";
    public static final String USER_DAO = "User Dao";

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        sce.getServletContext().setAttribute(ADDRESS_DAO, new MySQLAddressDAO());
        sce.getServletContext().setAttribute(EMAIL_DAO, new MySQLEmailDAO());
        sce.getServletContext().setAttribute(TEMPLATE_DAO, new MySQLTemplateDAO());
        sce.getServletContext().setAttribute(USER_DAO, new MySQLUserDAO());
    }
}
