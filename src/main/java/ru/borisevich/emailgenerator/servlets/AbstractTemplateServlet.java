package ru.borisevich.emailgenerator.servlets;

import ru.borisevich.emailgenerator.functional.Generator;

import javax.servlet.http.HttpServlet;
import org.apache.commons.lang3.StringUtils;

/**
 * Created by Leonid on 26.09.2016.
 */
public class AbstractTemplateServlet extends HttpServlet{
    protected boolean checkTemplateCorrect(String template){
        for(String s : Generator.KEYWORDS) {
            if (!StringUtils.containsIgnoreCase(template, s)){
                return false;
            }
        }
        return false;
    }
}
