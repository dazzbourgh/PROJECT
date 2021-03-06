package test;

import ru.borisevich.emailgenerator.db.mysql.MySQLAddressDAO;
import ru.borisevich.emailgenerator.db.mysql.MySQLTemplateDAO;
import ru.borisevich.emailgenerator.model.Address;
import ru.borisevich.emailgenerator.model.Email;
import ru.borisevich.emailgenerator.functional.Generator;
import org.junit.Test;
import ru.borisevich.emailgenerator.model.Template;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

/**
 * Created by Leonid on 13.09.2016.
 */
public class TestGenerator {
    //@Before
    public void setup(){
        String text = "";
        StringBuilder stringBuilder = new StringBuilder();
        try {
            Files.lines(Paths.get("C:\\Users\\Leonid\\Documents\\PROJECT\\src\\main\\resources\\template.txt")).
                    forEach((string) -> stringBuilder.append(string));
            text = stringBuilder.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        new MySQLTemplateDAO().insertTemplate(new Template(0, text));
        //template = new MySQLTemplateDAO().getById(0);
        System.out.println(text);
    }
    //@After
    public void destroy(){
        new MySQLTemplateDAO().deleteTemplate(new Template(0, ""));
    }
/*
    @Test
    public void test(){
        String trackInfo = "AUTHOR: dazzbourgh;TITLE: testTitle;TARGET: test;INFO: some info;STYLE: house;BPM: 128;LINK: some link;NAME: Leonid";
        List<Address> addressList = new MySQLAddressDAO().findAll();
        Address[] a = new Address[addressList.size()];
        a = addressList.toArray(a);
        Email e = new Generator().generateMails(addressList.toArray(a), trackInfo).get(0);
        System.out.println(e.getText());
    }
*/
}
