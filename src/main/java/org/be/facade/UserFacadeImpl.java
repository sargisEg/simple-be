package org.be.facade;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.util.Strings;
import org.be.controller.dto.RegisterUserRequestDto;
import org.be.controller.dto.UserDto;
import org.be.entity.User;
import org.be.service.core.UserService;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class UserFacadeImpl implements UserFacade {

    private final UserService userService;

    @Override
    public UserDto registerUser(RegisterUserRequestDto dto) {
        log.info("Registering user for provided request - {}", dto);
        if (Strings.isBlank(dto.getUsername()) || Strings.isBlank(dto.getPassword())) {
            return new UserDto("Request body values should not be null or empty");
        }

        final User user = userService.create(dto.getUsername(), dto.getPassword());

        final UserDto userDto = new UserDto(user.getId(), user.getUsername(), null);

        log.info("Successfully registered user for provided request - {}, response - {}", dto, userDto);
        return userDto;
    }
}