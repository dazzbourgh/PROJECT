package test;

import borisevich.emailgenerator.db.MySQLAddressDAO;
import borisevich.emailgenerator.db.MySQLTemplateDAO;
import borisevich.emailgenerator.functional.Address;
import borisevich.emailgenerator.functional.Generator;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

/**
 * Created by Leonid on 13.09.2016.
 */
public class TestGenerator {
    String template;

    @Before
    void setup(){
        new MySQLTemplateDAO().insertTemplate("");
    }
    @After
    void destroy(){
        new MySQLTemplateDAO().deleteTemplate(0);
    }

    @Test
    boolean test(){
        List<Address> addressList = new MySQLAddressDAO().findAll();
        Address[] a = new Address[addressList.size()];
        new Generator().generateMails(addressList.toArray(a), template);
        return false;
    }
}
