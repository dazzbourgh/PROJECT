package borisevich.emailgenerator.db.MySQL;

import borisevich.emailgenerator.db.EmailDAO;
import borisevich.emailgenerator.functional.Email;
import org.apache.log4j.Logger;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
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
        } catch (SQLException e){
            LOGGER.debug("ERROR: can't connect to DB");
            LOGGER.debug(e);
            return Collections.emptyList();
        }
    }

    @Override
    public boolean insertEmail(Email email, int user_id) {
        LOGGER.debug("Inserting mail from user_id: " + user_id + "; To: " + email.getAddress());
        int recipient_id = 0;
        try (DBConnector dbConnector = new DBConnector()){
            recipient_id = new MySQLAddressDAO().findByAddress(email.getAddress()).getAddress_id();
            dbConnector.executeUpdate(
                    "INSERT INTO emails (send_date, address_id, user_id) " +
                            "VALUES (NOW(), \'" +
                            recipient_id + "\', \'" +
                            user_id + "\');");
            return true;
        } catch (SQLException e) {
            LOGGER.error("SQLException: can't insert new email to DB");
            LOGGER.debug("Date: " + new Date(new java.util.Date().getTime()).toString() + "; recipient: " + recipient_id);
        }
        return false;
    }
}
