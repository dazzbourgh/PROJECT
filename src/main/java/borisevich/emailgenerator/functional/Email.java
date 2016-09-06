package borisevich.emailgenerator.functional;

import java.util.Date;

public class Email{
    private String address;
    private String text;
    private Date date;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    private Email(){

    }

    public Email(String address, String text){
        this.address = address;
        this.text = text;
    }
}