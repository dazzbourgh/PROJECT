package borisevich.emailgenerator.db;

import borisevich.emailgenerator.functional.Email;

import java.util.List;

/**
 * Created by Leonid on 06.09.2016.
 */
public interface EmailDAO {
    List<Email> findByUserId(int id);

    boolean insertEmail(Email email, int user_id);
}
