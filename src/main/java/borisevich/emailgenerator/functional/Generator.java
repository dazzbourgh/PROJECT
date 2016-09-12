package borisevich.emailgenerator.functional;

import borisevich.emailgenerator.db.MySQLTemplateDAO;
import borisevich.emailgenerator.functional.Email;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

public class Generator{
    final static String[] KEYWORDS = {
            "target",
            "info",
            "author",
            "title",
            "style",
            "bpm",
            "link"
    };

    private Map<String, String> processTrackInfo(String trackInfoString) throws NullPointerException, IllegalArgumentException{
        for(String s : KEYWORDS) {
            if (!trackInfoString.contains(s)){
                throw new NullPointerException();
            }
        }
        Map<String, String> returnValue = new LinkedHashMap<String, String>();
        String[] strings = trackInfoString.split(";");

        if(KEYWORDS.length != strings.length){
            throw new IllegalArgumentException("7 arguments required: " +
                    "target, info, author, title, style, bpm, link.");
        }

        for(String s : strings){
            String[] s1 = s.split(": ");
            returnValue.put(s1[0], s1[1]);
        }
        return returnValue;
    }
    private String loadTemplate(){
        return new MySQLTemplateDAO().getRandomTemplate();
    }
    public Email[] generateMails(Address[] addressees, String context) throws NullPointerException{
        Map<String, String> trackInfo = processTrackInfo(context);
        Email[] emails = new Email[addressees.length];
        String template = loadTemplate();
        for(int i = 0; i < addressees.length; i++){
            emails[i].setAddress(addressees[i].getAddress());
            String text = new String(template);
            for(Map.Entry<String, String> entry : trackInfo.entrySet()){
                text.replace(entry.getKey(), entry.getValue());
            }
            emails[i].setText(text);
            emails[i].setDate(new Date());
        }
        return emails;
    }
}