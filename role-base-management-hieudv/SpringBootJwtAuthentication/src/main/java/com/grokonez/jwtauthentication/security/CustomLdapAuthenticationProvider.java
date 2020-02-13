package com.grokonez.jwtauthentication.security;

import com.grokonez.jwtauthentication.message.request.Credential;
import com.grokonez.jwtauthentication.security.services.LdapConnection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class CustomLdapAuthenticationProvider implements AuthenticationProvider {
    @Autowired
    LdapConnection ldapConnection;
    @Autowired
    Credential cd;


    @Override
    public Authentication authenticate(Authentication authentication){


        String name = authentication.getName();
        String password = authentication.getCredentials().toString();
        cd.setUserName(name);
        cd.setUserPassword(password);

        if (ldapConnection.checkConnection(cd)) {

            // use the credentials
            // and authenticate against the third-party system
            return new UsernamePasswordAuthenticationToken(
                    name, password, new ArrayList<>());
        } else {
            return null;
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }

}
