package com.grokonez.jwtauthentication.security.services;

import com.grokonez.jwtauthentication.message.request.Credential;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.filter.AndFilter;
import org.springframework.ldap.filter.EqualsFilter;
import org.springframework.stereotype.Service;

import java.util.logging.Logger;


@Service
public class LdapConnection {
    private static final Logger LOGGER = Logger.getLogger("LdapConnection");

    @Autowired
    private LdapTemplate ldapTemplate;

    public boolean checkConnection(Credential credential){
        AndFilter filter = new AndFilter();
        filter.and(new EqualsFilter("objectclass", "person")).and(new EqualsFilter("cn", credential.getUserName()));
        boolean auth = ldapTemplate.authenticate(""
                ,filter.toString()
                ,credential.getUserPassword());

        LOGGER.info(String.valueOf(auth));

        return auth;
    }
}
