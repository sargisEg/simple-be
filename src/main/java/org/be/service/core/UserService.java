package org.be.service.core;

import org.be.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    User create(String username, String password);

    void deleteById(Long id);

    Optional<User> findByUsername(String username);

    Optional<User> findById(Long id);

    List<User> findAll();
}
