package borisevich.emailgenerator.db;

import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.*;


/**
 * Created by Leonid on 23.08.2016.
 */

public class DBConnector {
    static final Logger logger = Logger.getLogger(DBConnector.class.getName());
    // JDBC driver name and database URL
    static final String JDBC_DRIVER="com.mysql.jdbc.Driver";
    static final String DB_URL="jdbc:mysql://localhost/emails";

    //  Database credentials
    static final String USER = "root";
    static final String PASS = "qwerty123";

    private Connection connection;
    private ResultSet resultSet;
    private Statement statement;

    static {
        // Register JDBC driver
        try {
            Class.forName("com.mysql.jdbc.Driver");
            logger.info("Registered JDBC driver successfully");
        }catch(ClassNotFoundException e){
            //TODO: add logging here
            logger.info("Failed to register JDBC driver: " + e.getMessage());
        }
    }

    public DBConnector(){}

    public ResultSet executeStatement(String sqlCommand){
        try{
            // Open a connection
            connection = DriverManager.getConnection(DB_URL, USER, PASS);

            // Execute SQL query
            statement = connection.createStatement();
            String sql;
            sql = sqlCommand;
            resultSet = statement.executeQuery(sql);

            // Clean up environment
            //rs.close();
            //stmt.close();
            //conn.close();
            return resultSet;
        }catch(SQLException se){
            //TODO: handle exceptions
            //Handle errors for JDBC
            se.printStackTrace();
        }catch(Exception e){
            //Handle errors for Class.forName
            e.printStackTrace();
        }
        return null;
    }
    public void close(){
        try{
            resultSet.close();
            statement.close();
            connection.close();
        }catch(SQLException e){
            logger.debug("Error while closing DBConnection");
        }
    }
    public static void main(String[] args){
        ResultSet rstest = new DBConnector().executeStatement("SELECT * " +
                "FROM USERS " +
                "WHERE username=\'test\';"
        );
        try {
            rstest.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            System.out.println(rstest.getString("username"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
