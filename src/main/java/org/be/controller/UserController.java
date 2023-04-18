package org.be.controller;

import lombok.RequiredArgsConstructor;
import org.apache.catalina.LifecycleState;
import org.be.controller.dto.RegisterRequestDto;
import org.be.controller.dto.SignInRequestDto;
import org.be.controller.dto.UserDto;
import org.be.facade.UserFacade;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api")
@RequiredArgsConstructor
public class UserController {

    private final UserFacade userFacade;

    @PostMapping("register")
    ResponseEntity<UserDto> registerUser(RegisterRequestDto dto) {
        final UserDto userDto = userFacade.registerUser(dto);
        return ResponseEntity.status(userDto.getStatus())
                .body(userDto);
    }

    @PostMapping("sign-in")
    ResponseEntity<UserDto> signIn(SignInRequestDto dto) {
        final UserDto userDto = userFacade.signIn(dto);
        return ResponseEntity.status(userDto.getStatus())
                .body(userDto);
    }

    @GetMapping("users")
    ResponseEntity<List<UserDto>> getAllUsers() {
        final List<UserDto> allUsers = userFacade.getAllUsers();
        return ResponseEntity.ok(allUsers);
    }

    @DeleteMapping("users/{userId}")
    ResponseEntity<UserDto> deleteUser(@PathVariable("userId") Long userId) {
        final UserDto userDto = userFacade.deleteUserById(userId);
        return ResponseEntity.status(userDto.getStatus())
                .body(userDto);
    }
}
