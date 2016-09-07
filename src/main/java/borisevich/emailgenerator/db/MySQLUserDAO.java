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
    public boolean checkPassword(User user) {
        try(DBConnector dbConnector = new DBConnector()) {

            ResultSet rs = dbConnector.executeStatement("SELECT * " +
                    "FROM USERS " +
                    "WHERE username=\'" + user.getUsername() +
                    "\' && password=\'" + user.getPassword() +
                    "\';"
            );

            if (rs.next()) {
                return true;
            }
            //dbConnector.close();
        } catch (SQLException e){
            LOGGER.debug("ERROR: can't connect to DB");
            return false;
        }
        return false;
    }

    @Override
    public int getUserId(String name) {
        try(DBConnector dbConnector = new DBConnector()) {

            ResultSet rs = dbConnector.executeStatement("SELECT user_id " +
                    "FROM USERS " +
                    "WHERE username=\'" + name +
                    "\';"
            );

            if (rs.next()) {
                return rs.getInt("user_id");
            }
            //dbConnector.close();
        } catch (SQLException e){
            LOGGER.debug("ERROR: can't connect to DB");
            return -1;
        }
        return -1;
    }

    @Override
    public List<User> findByName(String name) {
        List<User> returnValue = new LinkedList<>();
        try (DBConnector dbConnector = new DBConnector()){
            ResultSet resultSet = dbConnector.executeStatement(
                    "SELECT * FROM USERS " +
                            "WHERE username=\'" +
                            name +
                            "\';");
            if(!resultSet.next())
                return Collections.emptyList();
            resultSet.beforeFirst();
            while(resultSet.next()){
                returnValue.add(new User(resultSet.getInt("user_id"), resultSet.getString("username"), "NO_PASSWORD_NEEDED"));
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
