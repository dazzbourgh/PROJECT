package borisevich.emailgenerator;

public class Generator{
    public Email[] generateMails(String[] addressees, String context){
        Email[] emails = new Email[addressees.length];
        for(int i = 0; i < addressees.length; i++){
            emails[i].setAddressee(addressees[i]);
            emails[i].setText(context);
        }

        return emails;
    }
}