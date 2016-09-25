package ru.borisevich.emailgenerator.model;

/**
 * Created by Leonid on 22.09.2016.
 */
public class Template {
    int template_id;
    String text;

    public int getTemplate_id() {
        return template_id;
    }

    public void setTemplate_id(int template_id) {
        this.template_id = template_id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
