package borisevich.emailgenerator.db;

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
                    "SELECT COUNT(*) FROM" +
                    "templates;"
            );
            total = rs.getInt("total");
        } catch (SQLException e){
            LOGGER.error("Can't count from templates");
        }
        randomNumber = new Random().nextInt(total - 1);
        try(DBConnector dbConnector = new DBConnector()) {
            ResultSet rs = dbConnector.executeStatement(
                    "SELECT * FROM" +
                            "templates" +
                            "WHERE template_id=\'" + randomNumber +"\';"
            );
            return rs.getString("text");
        } catch (SQLException e){
            LOGGER.error("Can't get template or extract text");
        }
        return null;
    }
}
