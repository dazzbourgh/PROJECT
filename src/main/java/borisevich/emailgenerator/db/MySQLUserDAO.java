package borisevich.emailgenerator.db;

import borisevich.emailgenerator.functional.User;
import org.apache.log4j.Logger;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Leonid on 05.09.2016.
 */
public class MySQLUserDAO implements UserDAO {
    private static final Logger LOGGER = Logger.getLogger(MySQLUserDAO.class.getName());

    @Override
    public List<User> findByName(String username) {
        List<User> returnValue = new LinkedList<>();
        try (DBConnector dbConnector = new DBConnector()){
            ResultSet resultSet = dbConnector.executeStatement(
                    "SELECT * FROM USERS " +
                            "WHERE username=\'" +
                            username +
                            "\';");
            if(!resultSet.next())
                return Collections.emptyList();
            resultSet.beforeFirst();
            while(resultSet.next()){
                returnValue.add(new User(resultSet.getString("username"), "NO_PASSWORD_NEEDED"));
            }
        } catch (SQLException e) {
            LOGGER.error("SQLException: can't check user's existence");
        }
        return returnValue;
    }

    @Override
    public boolean insertUser(User user) {
        try (DBConnector dbConnector = new DBConnector()){
            dbConnector.executeUpdate(
                    "INSERT INTO users (username, password)" +
                            "VALUES (\'" + user.getUsername() + "\', \'" +
                            user.getPassword() + "\');");
            return true;
        } catch (SQLException e) {
            LOGGER.error("SQLException: can't insert new user to DB");
        }
        return false;
    }

    @Override
    public boolean updateUser(User user) {
        return false;
    }

    @Override
    public boolean deleteUser(User user) {
        try (DBConnector dbConnector = new DBConnector()){
            dbConnector.executeUpdate(
                    "DELETE FROM users WHERE username=\'" +
                            user.getUsername() +
                            "\';");
            return true;
        } catch (SQLException e) {
            LOGGER.error("SQLException: can't insert new user to DB");
        }
        return false;
    }
}
