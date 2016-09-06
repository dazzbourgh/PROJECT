package borisevich.emailgenerator.db;

import borisevich.emailgenerator.functional.Address;

import java.util.List;

/**
 * Created by Leonid on 06.09.2016.
 */
public interface AddressDAO {
    List<Address> findByName(String name);
    List<Address> findAll();

    boolean checkPassword(Address address);
    boolean insertLabel(Address address);
    boolean updateLabel(Address address);
    boolean deleteLabel(Address address);
}
