package de.stuckenbrockhm.demo.monitoring.app;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import de.stuckenbrockhm.demo.monitoring.api.AuthBaseApi;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    RestTemplate rest = new RestTemplate();

    @Value("${demo-monitoring-app.service.auth.url}")
    private String authServiceHost = "http://demo-monitoring-auth:9001";

    @Override
    public Authentication authenticate(Authentication authentication)
      throws AuthenticationException {

        String name = authentication.getName();

        String password = authentication.getCredentials().toString();

        Optional<String> role = Optional.empty();

        try{
            AuthBaseApi auth = authUser(name, encryptPassword(password));
            if(auth != null && auth.getAuthenticated()){
                role = Optional.of("ROLE_USER");
            }
        }catch(NoSuchAlgorithmException e){
            throw new BadCredentialsException(e.getMessage());
        }

        if(!role.isPresent()){
            throw new BadCredentialsException("Username/Passwort invalid");
        }

        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(role.get()));
        return new UsernamePasswordAuthenticationToken(name, password, authorities);
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }

    private AuthBaseApi authUser(String username, String passwordHash) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> map= new LinkedMultiValueMap<>();
        map.add("username", username);
        map.add("passwordHash", passwordHash);
        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(map, headers);

        return rest.postForObject(authServiceHost + "/auth", request, AuthBaseApi.class);
    }

    private static String encryptPassword(String password) throws NoSuchAlgorithmException {
        MessageDigest crypt = MessageDigest.getInstance("SHA-1");
        crypt.reset();
        crypt.update(password.getBytes(StandardCharsets.UTF_8));
        return new BigInteger(1, crypt.digest()).toString(16);
    }

}