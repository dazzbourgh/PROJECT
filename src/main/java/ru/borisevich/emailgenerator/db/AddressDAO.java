package ru.borisevich.emailgenerator.db;

import ru.borisevich.emailgenerator.model.Address;

import java.util.List;

/**
 * Created by Leonid on 06.09.2016.
 */

/**
 * DAO interface to work with {@code Address}.
 */
public interface AddressDAO {
    /**
     * Finds an {@code Address} by the name of organisation.
     * @param name
     * @return {@code Address} object from, corresponding to the name.
     */
    Address findByName(String name);

    /**
     * Finds an {@code Address} by email address of organisation.
     * @param address
     * @return {@code Address} object from, corresponding to email address.
     */
    Address findByAddress(String address);

    /**
     * Finds all addresses from database.
     * @return a {@code List} of {@code Address} objects.
     */
    List<Address> findAll();

    /**
     * Inserts {@code Address} to database.
     * @param address
     * @return true if succeeds, false if exception occurs.
     */
    boolean insertAddress(Address address);

    /**
     * Updates {@code Address} by {@code address_id} in database.
     * @param address with {@code address_id} of element, which
     *                is to be updated. Other fields may contain
     *                any information, but this field must be
     *                an id of existing object in database.
     * @return true if succeeds, false if exception occurs.
     */
    boolean updateAddress(Address address);
    //TODO: imlement in classes
    boolean deleteAddress(Address address);
}
