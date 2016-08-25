package borisevich.emailgenerator;

/**
 * Created by Leonid on 23.08.2016.
 */

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.apache.tomcat.jdbc.pool.PoolProperties;

public class DBConnector {
    private static DBConnector instance;
    private DBConnector(){}
    public DBConnector getInstance(){
        if(instance == null){
            instance = new DBConnector();
        }
        return instance;
    }


}
