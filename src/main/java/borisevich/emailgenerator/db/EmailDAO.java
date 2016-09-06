package borisevich.emailgenerator.db;

import borisevich.emailgenerator.functional.Email;

import java.util.List;

/**
 * Created by Leonid on 06.09.2016.
 */
public interface EmailDAO {
    List<Email> findByUserId(int id);
    List<Email> findByAddressId(int id);

    boolean checkPassword(Email email);
    boolean insertEmail(Email email);
    boolean updateEmail(Email email);
    boolean deleteEmail(Email email);
}
