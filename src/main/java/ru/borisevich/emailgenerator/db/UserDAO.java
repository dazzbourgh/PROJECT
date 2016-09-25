package ru.borisevich.emailgenerator.db;

import ru.borisevich.emailgenerator.model.User;

import java.util.List;

/**
 * Created by Leonid on 05.09.2016.
 */
public interface UserDAO {
    List<User> findByName(String name);

    int getUserId(String name);
    boolean checkPassword(User user);
    boolean insertUser(User user);
    boolean updateUser(User user);
    boolean deleteUser(User user);
}