package org.be.facade;

import org.be.controller.dto.RegisterRequestDto;
import org.be.controller.dto.SignInRequestDto;
import org.be.controller.dto.UserDto;

public interface UserFacade {

    UserDto registerUser(RegisterRequestDto dto);
    UserDto signIn(SignInRequestDto dto);
}
