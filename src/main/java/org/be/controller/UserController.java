package org.be.controller;

import lombok.RequiredArgsConstructor;
import org.be.controller.dto.RegisterUserRequestDto;
import org.be.controller.dto.UserDto;
import org.be.facade.UserFacade;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserFacade userFacade;

    @PostMapping
    ResponseEntity<UserDto> createUser(RegisterUserRequestDto dto) {
        return null;
    }
}
