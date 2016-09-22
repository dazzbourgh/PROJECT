package borisevich.emailgenerator.handlers;

import org.apache.log4j.Logger;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;

/**
 * Created by Leonid on 22.09.2016.
 */
public class LogTag extends SimpleTagSupport {
    @Override
    public void doTag() throws JspException, IOException {
        PageContext pageContext = (PageContext)this.getJspContext();
        Logger logger = Logger.getLogger(pageContext.getPage().toString());
        logger.info("Inside of: " + pageContext.getPage());
    }
}
