package borisevich.emailgenerator.db;

/**
 * Created by Leonid on 12.09.2016.
 */
public interface TemplateDAO {
    String getRandomTemplate();
    String getById(int template_id);
    boolean insertTemplate(String template);
    boolean deleteTemplate(int template_id);
}
