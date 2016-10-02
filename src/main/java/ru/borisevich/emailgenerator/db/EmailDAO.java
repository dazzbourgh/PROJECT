package ru.borisevich.emailgenerator.db;

import ru.borisevich.emailgenerator.model.Email;

import java.util.List;

/**
 * Created by Leonid on 06.09.2016.
 */

/**
 * DAO interface to works with {@code Email}
 */
public interface EmailDAO {
    /**
     * Finds all emails sent by user with provided {@code user_id}.
     * @param id {@code user_id} of desired user.
     * @return a {@code List} of {@code Email} objects, representing
     *         all user's emails.
     */
    List<Email> findByUserId(int id);

    /**
     * Inserts an {@code Email} to database and maps relation to
     * user with specified {@code user_id}.
     * @param email {@code Email} object of email, that was sent.
     * @param user_id id of user in database.
     * @return true on success, false on fail.
     */
    boolean insertEmail(Email email, int user_id);
}
