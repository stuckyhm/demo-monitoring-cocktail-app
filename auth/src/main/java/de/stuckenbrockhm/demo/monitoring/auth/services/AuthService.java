package de.stuckenbrockhm.demo.monitoring.auth.services;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.springframework.stereotype.Service;

import de.stuckenbrockhm.demo.monitoring.api.AuthBaseApi;
import de.stuckenbrockhm.demo.monitoring.api.AuthFullApi;

@Service
public class AuthService {

    static final String LOGIN_COUNTER_NAME = "demo-monitoring-auth.authenticateUser.counter";

    public AuthBaseApi authenticateUser(String username, String passwordHash) throws NoSuchAlgorithmException {

        if(username == null){
            return new AuthFullApi(false, "Empty-Username");
        }else if(passwordHash == null){
            return new AuthFullApi(false, "Empty-Password");
        }else if(username.charAt(0) % 2 == 0){
            return new AuthFullApi(false, "Invalid-User");
        }else if(!encryptPassword(new StringBuilder(username).reverse().toString()).equals(passwordHash)){
            return new AuthFullApi(false, "Invalid-Password");
        }else{
            return new AuthFullApi(true, "Valid");
        }
    }

    private static String encryptPassword(String password) throws NoSuchAlgorithmException {

        MessageDigest crypt = MessageDigest.getInstance("SHA-1");
        crypt.reset();
        crypt.update(password.getBytes(StandardCharsets.UTF_8));

        return new BigInteger(1, crypt.digest()).toString(16);
    }

}
