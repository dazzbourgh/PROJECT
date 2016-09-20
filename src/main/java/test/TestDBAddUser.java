package test;

import borisevich.emailgenerator.db.MySQL.DBConnector;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.Assert.assertEquals;

/**
 * Created by Leonid on 04.09.2016.
 */
public class TestDBAddUser {
    String username = "test_user";

    @Before
    public void setUp(){
        System.out.println("Creating new table...");
        try(DBConnector dbConnector = new DBConnector()){
            dbConnector.executeUpdate("CREATE TABLE test_table " +
                    "(username varchar(15), password varchar(15));");
            System.out.println("Success");
        } catch (SQLException e){
            System.out.println("Cannot create new table");
            e.printStackTrace();
            return;
        }
    }

    @After
    public void destroy(){
        System.out.println("Dropping table...");
        try(DBConnector dbConnector = new DBConnector()){
            dbConnector.executeUpdate("DROP TABLE test_table");
            System.out.println("Success");
        } catch (SQLException e){
            System.out.println("Cannot drop table");
            return;
        }
    }

    @Test
    public void testRegisterNewUser() {
        System.out.println("Inside testRegisterNewUser()");

        System.out.println("Inserting new user...");
        try(DBConnector dbConnector = new DBConnector()){
            dbConnector.executeUpdate("INSERT INTO test_table (username, password) " +
                    "VALUES (\'test_user\', \'test_pass\');");
            System.out.println("Success");
        } catch (SQLException e){
            System.out.println("Cannot insert new user");
            return;
        }
        System.out.println("Checking new user...");
        try(DBConnector dbConnector = new DBConnector()){
            ResultSet rs = dbConnector.executeStatement("SELECT * FROM test_table");
            rs.next();
            assertEquals("test_user", rs.getString("username"));
            System.out.println("Success");
        } catch (SQLException e){
            System.out.println("Cannot insert new user");
            e.printStackTrace();
            return;
        }
    }
}
