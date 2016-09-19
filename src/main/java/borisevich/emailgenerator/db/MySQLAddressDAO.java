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
    private static final Logger LOGGER = Logger.getLogger(MySQLAddressDAO.class.getName());

    @Override
    public Address findByName(String name) {
        try (DBConnector dbConnector = new DBConnector()) {
            ResultSet rs = dbConnector.executeStatement("SELECT * FROM addresses " +
                    "WHERE name=\'" + name + "\';");
            rs.next();
            return new Address(rs.getInt("address_id"),
                    rs.getString("address"),
                    rs.getString("name"));
        } catch (SQLException e) {
            LOGGER.error("Error while getting address from DB by name");
        }
        return null;
    }

    @Override
    public Address findByAddress(String address) {
        try (DBConnector dbConnector = new DBConnector()) {
            ResultSet rs = dbConnector.executeStatement("SELECT * FROM addresses " +
                    "WHERE address=\'" + address + "\';");
            rs.next();
            return new Address(rs.getInt("address_id"),
                    rs.getString("address"),
                    rs.getString("name"));
        } catch (SQLException e) {
            LOGGER.error("Error while getting address from DB by name");
        }
        return null;
    }

    @Override
    public List<Address> findAll() {
        try (DBConnector dbConnector = new DBConnector()) {
            ResultSet rs = dbConnector.executeStatement("SELECT * FROM addresses");
            List<Address> addressList = new ArrayList<>();
            while (rs.next()) {
                addressList.add(new Address(rs.getInt("address_id"), rs.getString("address"), rs.getString("name")));
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
    public boolean insertAddress(Address address) {
        try (DBConnector dbConnector = new DBConnector()) {
            dbConnector.executeUpdate("INSERT INTO addresses " +
                    "(address,name)" +
                    "VALUES (\'" + address.getAddress() + "\',\'" + address.getName() + "\');"
            );
        } catch (SQLException e) {
            LOGGER.error("Can not insert address");
            LOGGER.error(e);
            return false;
        }
        return true;
    }

    @Override
    public boolean updateAddress(Address address) {
        try (DBConnector dbConnector = new DBConnector()) {
            dbConnector.executeUpdate("UPDATE addresses " +
                    "SET address=\'" + address.getAddress() + "\'," +
                    "name=\'" + address.getName() + "\' " +
                    "WHERE address_id=\'" + address.getAddress_id() + "\';"
            );
        } catch (SQLException e) {
            LOGGER.error("Can not update address");
            LOGGER.error(e);
            return false;
        }
        return true;
    }

    @Override
    public boolean deleteAddress(Address address) {
        return false;
    }
}
