package ru.borisevich.emailgenerator.db.mysql;

import ru.borisevich.emailgenerator.db.TemplateDAO;
import org.apache.log4j.Logger;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;

/**
 * Created by Leonid on 12.09.2016.
 */
public class MySQLTemplateDAO implements TemplateDAO
{
    private static final Logger LOGGER = Logger.getLogger(MySQLTemplateDAO.class.getName());
    @Override
    public String getRandomTemplate() {
        int total = 0;
        int randomNumber = 0;
        try(DBConnector dbConnector = new DBConnector()) {
            ResultSet rs = dbConnector.executeStatement(
                    "SELECT COUNT(*) FROM " +
                    "templates;"
            );
            rs.next();
            total = rs.getInt("count(*)");
        } catch (SQLException e){
            LOGGER.error("Can't count from templates");
        }
        randomNumber = new Random().nextInt(total);
        try(DBConnector dbConnector = new DBConnector()) {
            ResultSet rs = dbConnector.executeStatement(
                    "SELECT * FROM " +
                            "templates " +
                            "WHERE template_id=\'" + randomNumber +"\';"
            );
            rs.next();
            String returnValue = rs.getString("text");
            LOGGER.debug("Template is: " + returnValue);
            return returnValue;
        } catch (SQLException e){
            LOGGER.error("Can't get template or extract text");
        }
        return null;
    }

    @Override
    public String getById(int template_id) {
        try(DBConnector dbConnector = new DBConnector()) {
            ResultSet rs = dbConnector.executeStatement(
                    "SELECT * FROM " +
                            "templates " +
                            "WHERE template_id=\'" + template_id +"\';"
            );
            rs.next();
            return rs.getString("text");
        } catch (SQLException e){
            LOGGER.error("Can't get template or extract text");
        }
        return null;
    }

    @Override
    public boolean insertTemplate(String template) {
        try(DBConnector dbConnector = new DBConnector()){
            dbConnector.executeUpdate("INSERT INTO templates " +
                    "(text) " +
                    "VALUES (\'" + template +"\');"
            );
        } catch (SQLException e){
            LOGGER.error("Can not insert template");
            LOGGER.error(e);
            return false;
        }
        return true;
    }

    @Override
    public boolean deleteTemplate(int template_id) {
        try(DBConnector dbConnector = new DBConnector()){
            dbConnector.executeUpdate("DELETE FROM templates " +
                    "WHERE template_id=\'" + template_id +"\';"
            );
        } catch (SQLException e){
            LOGGER.error("Can not delete template");
            LOGGER.error(e);
            return false;
        }
        return true;
    }
}
