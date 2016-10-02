package ru.borisevich.emailgenerator.db;

import ru.borisevich.emailgenerator.model.User;

import java.util.List;

/**
 * Created by Leonid on 05.09.2016.
 */

/**
 * DAO interface to works with {@code User}
 */
public interface UserDAO {

    List<User> findByName(String name);

    /**
     * Returns {@code user_id} by user's name.
     * @param name {@code username} of {@code User}.
     * @return {@code user_id} of {@code User} object from database.
     */
    int getUserId(String name);

    /**
     * Checks if passwords of specified {@code User} matches
     * to information in database.
     * @param user
     * @return true if credentials match, false if not.
     */
    boolean checkPassword(User user);

    /**
     * Inserts {@code User} into database.
     * @param user
     * @return true on success, false on failure.
     */
    boolean insertUser(User user);
    //TODO: add user management
    boolean updateUser(User user);
    boolean deleteUser(User user);
}
