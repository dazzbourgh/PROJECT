package borisevich.emailgenerator.db;

import borisevich.emailgenerator.functional.User;

import java.util.List;

/**
 * Created by Leonid on 05.09.2016.
 */
interface UserDAO {
    List<User> findByName(String username);

    boolean checkPassword(User user);
    boolean insertUser(User user);
    boolean updateUser(User user);
    boolean deleteUser(User user);
}
