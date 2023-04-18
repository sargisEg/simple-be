package org.be.facade;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.util.Strings;
import org.be.controller.dto.RegisterUserRequestDto;
import org.be.controller.dto.UserDto;
import org.be.entity.User;
import org.be.service.core.UserService;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Slf4j
@Component
@RequiredArgsConstructor
public class UserFacadeImpl implements UserFacade {

    private final UserService userService;

    @Override
    public UserDto registerUser(RegisterUserRequestDto dto) {
        log.info("Registering user with username - {} for provided request", dto.getUsername());
        if (Strings.isBlank(dto.getUsername()) || Strings.isBlank(dto.getPassword())) {
            return new UserDto("Request body values should not be null or empty", 404);
        }

        final Optional<User> optionalUser = userService.getByUsername(dto.getUsername());
        if (optionalUser.isPresent()) {
            return new UserDto("User with username - " + dto.getUsername() + " already exist", 409);
        }

        final User user = userService.create(dto.getUsername(), dto.getPassword());

        final UserDto userDto = new UserDto(user.getId(), user.getUsername(), null, 200);

        log.info("Successfully registered user username - {} for provided request, response - {}", dto.getUsername(), userDto);
        return userDto;
    }
}
