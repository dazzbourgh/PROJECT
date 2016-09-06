package borisevich.emailgenerator.db;

import borisevich.emailgenerator.functional.Address;
import org.apache.log4j.Logger;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Leonid on 06.09.2016.
 */
public class MySQLAddressDAO implements AddressDAO {
    private static final Logger LOGGER = Logger.getLogger(MySQLUserDAO.class.getName());

    @Override
    public List<Address> findByName(String name) {
        return null;
    }

    @Override
    public List<Address> findAll() {
        try (DBConnector dbConnector = new DBConnector()){
            ResultSet rs = dbConnector.executeStatement("SELECT * FROM addresses");
            List<Address> addressList = new ArrayList<>();
            while(rs.next()){
                addressList.add(new Address(rs.getInt("address_id"), rs.getString("address"), rs.getNString("name")));
            }
            return addressList;
        } catch (SQLException e) {
            LOGGER.error("Error while retrieving labels list from DB");
        }
        return Collections.emptyList();
    }

    @Override
    public boolean checkPassword(Address address) {
        return false;
    }

    @Override
    public boolean insertLabel(Address address) {
        return false;
    }

    @Override
    public boolean updateLabel(Address address) {
        return false;
    }

    @Override
    public boolean deleteLabel(Address address) {
        return false;
    }
}
