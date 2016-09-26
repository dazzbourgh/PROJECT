package ru.borisevich.emailgenerator.db;

import ru.borisevich.emailgenerator.model.Template;

import java.util.List;

/**
 * Created by Leonid on 12.09.2016.
 */
public interface TemplateDAO {
    Template getRandomTemplate();
    Template getById(int template_id);
    List<Template> getAll();
    boolean insertTemplate(Template template);
    boolean deleteTemplate(Template template);
    boolean updateTemplate(Template template);
}
