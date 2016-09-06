package borisevich.emailgenerator.db;

import borisevich.emailgenerator.functional.Email;

import java.util.List;

/**
 * Created by Leonid on 06.09.2016.
 */
public class MySQLEmailDAO implements EmailDAO {
    @Override
    public List<Email> findByUserId(int id) {
        return null;
    }

    @Override
    public List<Email> findByAddressId(int id) {
        return null;
    }

    @Override
    public boolean checkPassword(Email email) {
        return false;
    }

    @Override
    public boolean insertEmail(Email email) {
        return false;
    }

    @Override
    public boolean updateEmail(Email email) {
        return false;
    }

    @Override
    public boolean deleteEmail(Email email) {
        return false;
    }
}
