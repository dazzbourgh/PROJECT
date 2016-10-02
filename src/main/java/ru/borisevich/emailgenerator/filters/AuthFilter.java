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

/**
 * Filters all the incoming requests to detect whether user
 * is logged in or not. Depending on that it is being chosen
 * which response user receives.
 */
@WebFilter(filterName = "AuthFilter",
urlPatterns = {"/*"})
public class AuthFilter implements Filter {
    private static final Logger LOGGER = Logger.getLogger(AuthFilter.class.getName());

    /**
     * List of resources which do not require authorization
     * to access.
     */
    private static final String[] ignoreList = {
            "register.jsp",
            "login.jsp",
            "index.jsp",
            "languageChoice",
            "register",
            ""
    };

    /**
     * Checks if user is logged in.
     * @param req
     * @return true if user's token is presented in {@code AuthTokenContainer},
     * false if not.
     */
    private boolean checkAuth(HttpServletRequest req){
        Object token = req.getSession().getAttribute("token");
        if(token != null){
            if(AuthTokenContainer.getInstance().containsToken(token.toString()))
                return true;
            LOGGER.info("User tried to login with token = " + token);
            for(Object o : AuthTokenContainer.getInstance().getAllTokens())
                LOGGER.info("Token in container = " + o);
        }
        return false;
    }
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    /**
     * Filters requests: forwards to login page if unauthorized user
     * tries to access content which is for authorized users only.
     * @param servletRequest
     * @param servletResponse
     * @param filterChain
     * @throws IOException
     * @throws ServletException
     */
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        for(String s : ignoreList){
            if(req.getRequestURI().equals("/ru.borisevich.emailgenerator/" + s)){
                req.getRequestDispatcher("/" + s).forward(servletRequest, servletResponse);
                return;
            }
        }
        if(!req.getRequestURI().equals("/ru.borisevich.emailgenerator/login")){
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
