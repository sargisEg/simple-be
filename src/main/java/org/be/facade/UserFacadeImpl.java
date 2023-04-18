package org.be.facade;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.util.Strings;
import org.be.controller.dto.RegisterRequestDto;
import org.be.controller.dto.SignInRequestDto;
import org.be.controller.dto.UserDto;
import org.be.entity.User;
import org.be.service.core.UserService;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Component
@RequiredArgsConstructor
public class UserFacadeImpl implements UserFacade {

    private final UserService userService;

    @Override
    public UserDto registerUser(RegisterRequestDto dto) {
        log.info("Registering user with username - {} for provided request", dto.getUsername());
        if (Strings.isBlank(dto.getUsername()) || Strings.isBlank(dto.getPassword())) {
            return new UserDto("Request body values should not be null or empty", 404);
        }

        final Optional<User> optionalUser = userService.findByUsername(dto.getUsername());
        if (optionalUser.isPresent()) {
            return new UserDto("User with username - " + dto.getUsername() + " already exist", 409);
        }

        final User user = userService.create(dto.getUsername(), dto.getPassword());

        final UserDto userDto = new UserDto(user.getId(), user.getUsername(), null, 200);

        log.info("Successfully registered user username - {} for provided request, response - {}", dto.getUsername(), userDto);
        return userDto;
    }

    @Override
    public UserDto signIn(SignInRequestDto dto) {
        log.info("Signing in user with username - {} for provided request", dto.getUsername());

        final Optional<User> optionalUser = userService.findByUsername(dto.getUsername());
        final boolean illegal = optionalUser
                .filter(user -> user.getPassword().equals(dto.getPassword()))
                .isEmpty();
        if (illegal) {
            return new UserDto("Illegal username or password", 401);
        }

        final UserDto userDto = new UserDto(optionalUser.get().getId(), dto.getUsername(), null, 200);

        log.info("Successfully signed in user with username - {} for provided request, response - {}", dto.getUsername(), userDto);
        return userDto;
    }

    @Override
    public List<UserDto> getAllUsers() {
        log.info("Getting all users for provided request");

        final List<User> users = userService.findAll();
        final List<UserDto> userDtoList = users.stream()
                .map(user -> new UserDto(user.getId(), user.getUsername(), null, 200))
                .collect(Collectors.toList());

        log.info("Successfully got all users for provided request, response - {}", userDtoList);
        return userDtoList;
    }

    @Override
    public UserDto deleteUserById(Long userId) {
        log.info("Deleting user with id - {} for provided request", userId);

        final Optional<User> optionalUser = userService.findById(userId);
        if (optionalUser.isEmpty()) {
            return new UserDto("Cannot find user with id - " + userId, 404);
        }

        userService.deleteById(userId);

        final UserDto userDto = new UserDto(userId, optionalUser.get().getUsername(), null, 200);

        log.info("Successfully deleted user with id - {} for provided request, response - {}", userId, userDto);
        return userDto;
    }
}
