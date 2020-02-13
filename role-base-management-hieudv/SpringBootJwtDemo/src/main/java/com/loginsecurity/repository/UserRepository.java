package com.loginsecurity.repository;

import com.loginsecurity.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findAllByUsername(String username);

    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);
}
