package ru.borisevich.emailgenerator.db.mysql;

import ru.borisevich.emailgenerator.db.TemplateDAO;
import org.apache.log4j.Logger;
import ru.borisevich.emailgenerator.model.Template;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * Created by Leonid on 12.09.2016.
 */
public class MySQLTemplateDAO implements TemplateDAO
{
    private static final Logger LOGGER = Logger.getLogger(MySQLTemplateDAO.class.getName());
    @Override
    public Template getRandomTemplate() {
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
            Template returnValue = new Template(
                    rs.getInt("template_id"),
                    rs.getString("text")
            );
            LOGGER.debug("Template is: " + returnValue);
            return returnValue;
        } catch (SQLException e){
            LOGGER.error("Can't get template or extract text");
        }
        return null;
    }

    @Override
    public Template getById(int template_id) {
        try(DBConnector dbConnector = new DBConnector()) {
            ResultSet rs = dbConnector.executeStatement(
                    "SELECT * FROM " +
                            "templates " +
                            "WHERE template_id=\'" + template_id +"\';"
            );
            rs.next();
            return new Template(
                    rs.getInt("template_id"),
                    rs.getString("text")
            );
        } catch (SQLException e){
            LOGGER.error("Can't get template or extract text");
        }
        return null;
    }

    @Override
    public List<Template> getAll() {
        List<Template> returnValue = new ArrayList<>();
        try(DBConnector dbConnector = new DBConnector()) {
            ResultSet rs = dbConnector.executeStatement(
                    "SELECT * FROM " +
                            "templates;"
            );
            while(rs.next()){
                returnValue.add(new Template(
                        rs.getInt("template_id"),
                        rs.getString("text")
                ));
            }
            return returnValue;
        } catch (SQLException e){
            LOGGER.error("Can't get template or extract text");
        }
        return Collections.EMPTY_LIST;
    }

    @Override
    public boolean insertTemplate(Template template) {
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
    public boolean deleteTemplate(Template template) {
        try(DBConnector dbConnector = new DBConnector()){
            dbConnector.executeUpdate("DELETE FROM templates " +
                    "WHERE template_id=\'" + template.getTemplate_id() +"\';"
            );
        } catch (SQLException e){
            LOGGER.error("Can not delete template");
            LOGGER.error(e);
            return false;
        }
        return true;
    }

    @Override
    public boolean updateTemplate(Template template) {
        try (DBConnector dbConnector = new DBConnector()) {
            dbConnector.executeUpdate("UPDATE templates " +
                    "SET template_id=" + template.getTemplate_id() + "," +
                    "text=\'" + template.getText() + "\' " +
                    "WHERE template_id=" + template.getTemplate_id() + ";"
            );
        } catch (SQLException e) {
            LOGGER.error("Can not update template");
            LOGGER.error(e);
            return false;
        }
        return true;
    }
}
