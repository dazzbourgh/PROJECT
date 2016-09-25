package ru.borisevich.emailgenerator.db;

import ru.borisevich.emailgenerator.model.Email;

import java.util.List;

/**
 * Created by Leonid on 06.09.2016.
 */
public interface EmailDAO {
    List<Email> findByUserId(int id);

    boolean insertEmail(Email email, int user_id);
}
