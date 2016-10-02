package ru.borisevich.emailgenerator.functional;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Leonid on 07.09.2016.
 */

/**
 * Class, containing tokens of all authorized users. It also manages
 * all tokens (generates, adds to {@code List} etc.)
 *
 * Only one instance is allowed.
 */
public class AuthTokenContainer {
    /**
     * List of active tokens for authorized users.
     */
    private static List<String> tokenList = new ArrayList<>();
    /**
     * Instance of class.
     */
    private static AuthTokenContainer instance;

    /**
     * Private constructor, which is forbidden to use.
     */
    private AuthTokenContainer(){}

    /**
     * A method to get instance of class.
     * @return new instance if there is no, active instance if
     * one was already created.
     */
    public synchronized static AuthTokenContainer getInstance(){
        if(instance == null){
            instance = new AuthTokenContainer();
        }
        return instance;
    }

    /**
     * Generates token, consisting of 32 random symbols.
     * @return {@code String} of random symbols.
     */
    public String generateToken(){
        SecureRandom random = new SecureRandom();
        return new BigInteger(130, random).toString(32);
    }

    /**
     * Adds token to container.
     * @param token
     */
    public synchronized void addToken(String token){
        tokenList.add(token);
    }

    public synchronized void removeToken(String token){
        tokenList.remove(token);
    }

    /**
     * Checks if token is presented in container.
     * @param token
     * @return true if token is valid, false if token is not presented.
     */
    public synchronized boolean containsToken(String token){
        if(tokenList.contains(token))
            return true;
        return false;
    }

    /**
     * Returns all valid tokens in container.
     * @return an array of tokens.
     */
    public synchronized Object[] getAllTokens(){
        return tokenList.toArray();
    }
}
