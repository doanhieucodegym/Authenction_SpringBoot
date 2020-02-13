package com.loginsecurity.security.services;

import com.loginsecurity.model.Role;
import com.loginsecurity.model.User;
import com.loginsecurity.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    UserRepository userRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userRepository.findAllByUsername(username).orElseThrow(() ->
        new UsernameNotFoundException("User Not Found with --> username or emai:"+username)
        );
        return UserPrinciple.build(user);
    }
}
