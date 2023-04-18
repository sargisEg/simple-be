package org.be.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

    private Long id;
    private String username;

    private String error;
    private Integer status;

    public UserDto(String error, Integer status) {
        this.status = status;
        this.error = error;
    }
}
