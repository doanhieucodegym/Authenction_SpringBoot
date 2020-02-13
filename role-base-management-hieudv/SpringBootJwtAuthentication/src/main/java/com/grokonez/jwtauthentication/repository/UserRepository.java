package com.grokonez.jwtauthentication.repository;

import com.grokonez.jwtauthentication.model.User;

public interface UserRepository {
    boolean findByUser(String username, String password);
    boolean add(User user);
    boolean update(User user);
    boolean delete(User user);

}
