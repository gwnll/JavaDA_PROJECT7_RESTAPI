package com.nnk.springboot.dto;

import com.nnk.springboot.constraint.ValidPassword;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * Add a DTO layer
 * Password validation from the form input
 * Before persisting to the database
 */
@Data
public class UserDTO {
    @NotBlank(message = "Username is mandatory")
    private String username;

    @ValidPassword
    @NotBlank(message = "Password is mandatory")
    private String password;

    @NotBlank(message = "FullName is mandatory")
    private String fullname;

    @NotBlank(message = "Role is mandatory")
    private String role;

}
