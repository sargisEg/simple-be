package org.be.facade;

import org.be.controller.dto.RegisterUserRequestDto;
import org.be.controller.dto.UserDto;

public interface UserFacade {

    UserDto registerUser(RegisterUserRequestDto dto);
}
