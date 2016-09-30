package ru.borisevich.emailgenerator.model;

import java.util.Date;

public class Email{
    private String address;
    private String subject;
    private String text;
    private Date date;

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public Date getDate() { return date; }

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

    public Email(){

    }

    public Email(String address, String text){
        this.address = address;
        this.text = text;
    }
}