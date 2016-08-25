package borisevich.emailgenerator;

public class Email{
    private String addressee;
    private String text;

    public String getAddressee() {
        return addressee;
    }

    public void setAddressee(String addressee) {
        this.addressee = addressee;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    private Email(){

    }

    public Email(String addressee, String text){
        this.addressee = addressee;
        this.text = text;
    }
}