package org.be.service.core;

import org.be.entity.User;

import java.util.Optional;

public interface UserService {

    User create(String username, String password);

    void deleteById(Long id);

    Optional<User> getByUsername(String username);

    Optional<User> getById(Long id);
}
