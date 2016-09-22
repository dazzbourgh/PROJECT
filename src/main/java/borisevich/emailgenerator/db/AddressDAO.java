package borisevich.emailgenerator.db;

import borisevich.emailgenerator.model.Address;

import java.util.List;

/**
 * Created by Leonid on 06.09.2016.
 */
public interface AddressDAO {
    Address findByName(String name);
    Address findByAddress(String address);
    List<Address> findAll();

    boolean checkPassword(Address address);
    boolean insertAddress(Address address);
    boolean updateAddress(Address address);
    boolean deleteAddress(Address address);
}
