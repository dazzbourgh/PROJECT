package ru.borisevich.emailgenerator.filters;

import ru.borisevich.emailgenerator.functional.AuthTokenContainer;
import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * Created by Leonid on 28.08.2016.
 */
@WebFilter(filterName = "AuthFilter",
urlPatterns = {"/*"})
public class AuthFilter implements Filter {
    private static final Logger LOGGER = Logger.getLogger(AuthFilter.class.getName());
    private static final String[] ignoreList = {
            "register.jsp",
            "login.jsp",
            "index.jsp",
            "languageChoice",
            "register",
            ""
    };
    private boolean checkAuth(HttpServletRequest req){
        Object token = req.getSession().getAttribute("token");
        if(token != null){
            if(AuthTokenContainer.getInstance().containsToken(token.toString()))
                return true;
            LOGGER.debug("User token = " + token);
            for(Object o : AuthTokenContainer.getInstance().getAllTokens())
                LOGGER.debug("Token in container = " + o);
        }
        return false;
    }
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        for(String s : ignoreList){
            if(req.getRequestURI().equals("/ru.borisevich.emailgenerator/" + s)){
                req.getRequestDispatcher("/" + s).forward(servletRequest, servletResponse);
                return;
            }
        }
        if(!req.getRequestURI().equals("/ru.borisevich.emailgenerator/login")){
            LOGGER.debug("requested URI: " + req.getRequestURI());
            if(!checkAuth(req))
                req.getRequestDispatcher("/login.jsp").forward(servletRequest, servletResponse);
            else{
                String[] s = req.getRequestURI().split("/");
                req.getRequestDispatcher("/" + s[s.length - 1]).forward(servletRequest, servletResponse);
            }
         }
        else{
            req.getRequestDispatcher("/login").forward(servletRequest, servletResponse);
        }
    }

    public void destroy() {

    }
}
