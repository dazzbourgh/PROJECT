package ru.borisevich.emailgenerator.functional;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Leonid on 07.09.2016.
 */
public class AuthTokenContainer {
    private static List<String> tokenList = new ArrayList<>();
    private static AuthTokenContainer instance;

    private AuthTokenContainer(){

    }

    public synchronized static AuthTokenContainer getInstance(){
        if(instance == null){
            instance = new AuthTokenContainer();
        }
        return instance;
    }

    public String generateToken(){
        SecureRandom random = new SecureRandom();
        return new BigInteger(130, random).toString(32);
    }

    public synchronized void addToken(String token){
        tokenList.add(token);
    }

    public synchronized void removeToken(String token){
        tokenList.remove(token);
    }

    public synchronized boolean containsToken(String token){
        if(tokenList.contains(token))
            return true;
        return false;
    }

    public synchronized Object[] getAllTokens(){
        return tokenList.toArray();
    }
}
