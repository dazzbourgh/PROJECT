package ru.borisevich.emailgenerator.db.mysql;

import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.*;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.apache.tomcat.jdbc.pool.PoolProperties;

/**
 * Created by Leonid on 23.08.2016.
 */

/**
 * The {@code DBConnector} class is the element between classes
 * that make requests and database. It stores all the necessary
 * information to work with database, such as username, password,
 * name of database, info about driver, pool etc.
 *
 * {@code AutoCloseable} is being implemented, so this class must
 * be used in try-with-resource constructions, because connections
 * must be returned to the pool after action is complete.
 */
public class DBConnector implements AutoCloseable{
    static final Logger LOGGER = Logger.getLogger(DBConnector.class.getName());
    // JDBC driver name and database URL
    /**JDBC driver name*/
    static final String JDBC_DRIVER="com.mysql.jdbc.Driver";
    /**Name of database*/
    static final String DB_URL="jdbc:mysql://localhost/emails";

    //  Database credentials
    static final String USER = "root";
    static final String PASS = "qwerty123";

    private Connection connection;
    private ResultSet resultSet;
    private Statement statement;

    /**
     * A pool of connections, which is being initialized
     * in as static block below.
     */
    private static PoolProperties poolProperties = new PoolProperties();
    /**
     * A source of connections.
     */
    private static DataSource datasource;

    static {
        // Register JDBC driver
        try {
            Class.forName("com.mysql.jdbc.Driver");
            DBConnector.setPoolProperties();
            LOGGER.info("Registered JDBC driver successfully");
        }catch(ClassNotFoundException e){
            LOGGER.info("Failed to register JDBC driver: " + e.getMessage());
        }
    }

    public DBConnector(){}

    /**
     * Pool initialization.
     */
    public static void setPoolProperties(){
        poolProperties.setUrl(DB_URL);
        poolProperties.setDriverClassName(JDBC_DRIVER);
        poolProperties.setUsername(USER);
        poolProperties.setPassword(PASS);
        poolProperties.setJmxEnabled(true);
        poolProperties.setTestWhileIdle(false);
        poolProperties.setTestOnBorrow(true);
        poolProperties.setValidationQuery("SELECT 1");
        poolProperties.setTestOnReturn(false);
        poolProperties.setValidationInterval(30000);
        poolProperties.setTimeBetweenEvictionRunsMillis(30000);
        poolProperties.setMaxActive(10);
        poolProperties.setInitialSize(1);
        poolProperties.setMaxWait(10000);
        poolProperties.setRemoveAbandonedTimeout(60);
        poolProperties.setMinEvictableIdleTimeMillis(30000);
        poolProperties.setMinIdle(1);
        poolProperties.setMaxIdle(10);
        poolProperties.setLogAbandoned(true);
        poolProperties.setRemoveAbandoned(true);
        poolProperties.setJdbcInterceptors(
                "org.apache.tomcat.jdbc.pool.interceptor.ConnectionState;"+
                        "org.apache.tomcat.jdbc.pool.interceptor.StatementFinalizer");
        datasource = new DataSource();
        datasource.setPoolProperties(poolProperties);
    }

    /**
     * A method to get information from database, which is being stored in
     * {@code ResultSet}. To access members in {@code ResultSet} use
     * {@code next()} method.
     *
     * @param sqlCommand A query to database which should be executed
     * @return A {@code ResultSet}, containing results of SQL query.
     * @throws SQLException Being thrown by either {@code Connection},
     *         {@code Statement} or {@code ResultSet} if
     *         something goes wrong while accessing database.
     */
    public ResultSet executeStatement(String sqlCommand) throws SQLException{
        // Open a connection
        connection = datasource.getConnection();
        // Execute SQL query
        statement = connection.createStatement();
        resultSet = statement.executeQuery(sqlCommand);

        return resultSet;
    }

    /**
     * Same as {@code executeStatement()}, but for statements which modify
     * the database (e.g. update, delete or add information).
     * @param sqlCommand a {@code ResultSet}, containing results of SQL query.
     * @return the row count for SQL Data Manipulation Language (DML) statements
     * @throws SQLException Being thrown by either {@code Connection},
     *         {@code Statement} or {@code ResultSet} if
     *         something goes wrong while accessing database.
     */
    public int executeUpdate(String sqlCommand) throws SQLException{
        connection = datasource.getConnection();
        // Execute SQL query
        statement = connection.createStatement();
        return statement.executeUpdate(sqlCommand);
    }

    /**
     * Implements {@code AutoCloseable} to be used in try-with-resources
     * constructions.
     */
    @Override
    public void close(){
        try {
            if(resultSet != null)
                resultSet.close();
            if(statement != null)
                statement.close();
            if(connection != null)
                connection.close();
        } catch (SQLException e) {
            LOGGER.error("Failed to close DB connection");
        }
    }
}
