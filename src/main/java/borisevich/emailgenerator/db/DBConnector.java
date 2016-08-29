package borisevich.emailgenerator.db;

import com.mysql.jdbc.*;

import java.io.*;
import java.sql.Connection;
import java.sql.Statement;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;


/**
 * Created by Leonid on 23.08.2016.
 */

public class DBConnector {
    static {
        // Register JDBC driver
        try {
            Class.forName("com.mysql.jdbc.Driver");
        }catch(ClassNotFoundException e){
            //TODO: add logging here
        }
    }
    // JDBC driver name and database URL
    static final String JDBC_DRIVER="com.mysql.jdbc.Driver";
    static final String DB_URL="jdbc:mysql://localhost/emails";

    //  Database credentials
    static final String USER = "root";
    static final String PASS = "qwerty123";

    public DBConnector(){}

    public ResultSet executeStatement(String statement){
        try{
            // Open a connection
            Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);

            // Execute SQL query
            Statement stmt = conn.createStatement();
            String sql;
            sql = statement;
            ResultSet rs = stmt.executeQuery(sql);

            // Clean-up environment
            rs.close();
            stmt.close();
            conn.close();
            return rs;
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
}
