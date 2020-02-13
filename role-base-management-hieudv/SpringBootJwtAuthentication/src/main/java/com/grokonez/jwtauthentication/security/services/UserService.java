package com.grokonez.jwtauthentication.security.services;

import com.grokonez.jwtauthentication.model.User;
import com.grokonez.jwtauthentication.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public boolean findByUser(String username, String userPassword) {
        String result = jdbcTemplate.queryForObject(
                "SELECT UI_pw_hash FROM hiro_user_info WHERE UI_username=?",
                new Object[]{username},
                String.class
        );

        return  BCrypt.checkpw(userPassword, result);
    }

    @Override
    public boolean add(User user) {
        return false;
    }

    @Override
    public boolean update(User user) {
        return false;
    }

    @Override
    public boolean delete(User user) {
        return false;
    }
}
