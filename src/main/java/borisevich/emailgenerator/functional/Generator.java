package borisevich.emailgenerator.functional;

import borisevich.emailgenerator.functional.Email;

import java.util.LinkedHashMap;
import java.util.Map;

public class Generator{
    final static String[] KEYWORDS = {
            "author",
            "title",
            "bpm",
            "style",
            "link"
    };
    //getLabels
    Map<String, String> processTrackInfo(String trackInfoString){
        for(String s : KEYWORDS) {
            if (!trackInfoString.contains(s)){
                throw new NullPointerException();
            }
        }
        Map<String, String> returnValue = new LinkedHashMap<String, String>();
        String[] strings = trackInfoString.split(";");
        for(String s : strings){
            String[] s1 = s.split(": ");
            returnValue.put(s1[0], s1[1]);
        }
        return returnValue;
    }
    public Email[] generateMails(String[] addressees, String context){
        Email[] emails = new Email[addressees.length];
        for(int i = 0; i < addressees.length; i++){
            emails[i].setAddressee(addressees[i]);
            emails[i].setText(context);
        }

        return emails;
    }
}