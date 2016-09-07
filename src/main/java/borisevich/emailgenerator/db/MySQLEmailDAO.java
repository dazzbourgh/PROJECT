package borisevich.emailgenerator.db;

import borisevich.emailgenerator.functional.Email;
import org.apache.log4j.Logger;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;



/**
 * Created by Leonid on 06.09.2016.
 */
public class MySQLEmailDAO implements EmailDAO {
    private static final Logger LOGGER = Logger.getLogger(MySQLEmailDAO.class.getName());

    @Override
    public List<Email> findByUserId(int id) {
        try(DBConnector dbConnector = new DBConnector()) {
            List<Email> returnValue = new LinkedList<>();
            ResultSet rs = dbConnector.executeStatement(
                    "SELECT emails.*, addresses.name " +
                    "FROM emails " +
                    "join addresses on emails.address_id=addresses.address_id " +
                    "WHERE user_id=\'" + id +
                    "\';"
            );

            while (rs.next()) {
                returnValue.add(new Email(rs.getString("addresses.name"), "EMPTY_TEXT"));
            }
            return returnValue;
            //dbConnector.close();
        } catch (SQLException e){
            LOGGER.debug("ERROR: can't connect to DB");
            LOGGER.debug(e);
            return Collections.emptyList();
        }
    }

    @Override
    public List<Email> findByAddressId(int id) {
        return null;
    }

    @Override
    public boolean checkPassword(Email email) {
        return false;
    }

    @Override
    public boolean insertEmail(Email email) {
        return false;
    }

    @Override
    public boolean updateEmail(Email email) {
        return false;
    }

    @Override
    public boolean deleteEmail(Email email) {
        return false;
    }
}
