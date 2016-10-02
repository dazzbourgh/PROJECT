package ru.borisevich.emailgenerator.db;

import ru.borisevich.emailgenerator.model.Template;

import java.util.List;

/**
 * Created by Leonid on 12.09.2016.
 */

/**
 * DAO interface to works with {@code Template}
 */
public interface TemplateDAO {
    /**
     * Returns a random template.
     * @return random {@code Template} from database.
     */
    Template getRandomTemplate();

    /**
     * Returns a template with specified {@code template_id}.
     * @param template_id
     * @return {@code Template} with specified id.
     */
    Template getById(int template_id);

    /**
     * Returns all templates from database.
     * @return {@code List} of {@code Template}.
     */
    List<Template> getAll();

    /**
     * @param template with any {@code template_id}. This field is
     *                 automatically assigned by database and is
     *                 being rewritten during insertion.
     * @return true on success, false on failure.
     */
    boolean insertTemplate(Template template);

    /**
     * Deletes template with {@code template_id} of parameter from database.
     * @param template to be deleted. Only {@code template_id} matters.
     * @return true on success, false on failure.
     */
    boolean deleteTemplate(Template template);

    /**
     * Updates template with {@code template_id} of {@code template}
     * in database with information of this {@code template}.
     * @param template
     * @return true on success, false on failure.
     */
    boolean updateTemplate(Template template);
}
