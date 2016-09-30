package ru.borisevich.emailgenerator.functional;

import org.apache.log4j.Logger;
import ru.borisevich.emailgenerator.db.mysql.MySQLTemplateDAO;
import ru.borisevich.emailgenerator.model.Address;
import ru.borisevich.emailgenerator.model.Email;
import ru.borisevich.emailgenerator.model.Template;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

public class Generator {
    private static final Logger LOGGER = Logger.getLogger(Generator.class);
    public final static String[] KEYWORDS = {
            "INFO",
            "NAME",
            "AUTHOR",
            "TITLE",
            "STYLE",
            "BPM",
            "LINK"
    };

    public Generator() {

    }

    private Map<String, String> processTrackInfo(HttpServletRequest req){

        Map<String, String> returnValue = new LinkedHashMap<String, String>();
        returnValue.put("AUTHOR", req.getParameter("author").toString());
        returnValue.put("TITLE", req.getParameter("title").toString());
        returnValue.put("STYLE", req.getParameter("style").toString());
        returnValue.put("BPM", req.getParameter("bpm").toString());
        returnValue.put("NAME", req.getParameter("name").toString());
        returnValue.put("LINK", req.getParameter("link").toString());
        returnValue.put("INFO", req.getParameter("trackInfo").toString());

        return returnValue;
    }

    private Template loadTemplate() {
        return new MySQLTemplateDAO().getRandomTemplate();
    }

    public List<Email> generateMails(Address[] addressees, HttpServletRequest req) throws IllegalArgumentException {
        Map<String, String> trackInfo = processTrackInfo(req);

        Email[] emails = new Email[addressees.length];
        Template template = loadTemplate();
        List<Email> returnValue = new ArrayList<>();
        for (int i = 0; i < addressees.length; i++) {
            String text = template.getText();
            for (Map.Entry<String, String> entry : trackInfo.entrySet()) {
                text = text.replace(entry.getKey(), entry.getValue());
            }
            text = text.replace("TARGET", addressees[i].getName());
            emails[i] = new Email();
            emails[i].setAddress(addressees[i].getAddress());
            emails[i].setSubject(trackInfo.get("AUTHOR")
                    + ": "
                    + trackInfo.get("TITLE"));
            emails[i].setText(text);
            emails[i].setDate(new Date());
            returnValue.add(emails[i]);
        }
        return returnValue;
    }
}