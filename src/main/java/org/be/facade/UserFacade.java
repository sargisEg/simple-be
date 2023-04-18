package org.be.facade;

import org.be.controller.dto.RegisterRequestDto;
import org.be.controller.dto.SignInRequestDto;
import org.be.controller.dto.UserDto;

import java.util.List;

public interface UserFacade {

    UserDto registerUser(RegisterRequestDto dto);

    UserDto signIn(SignInRequestDto dto);

    List<UserDto> getAllUsers();

    UserDto deleteUserById(Long userId);
}
