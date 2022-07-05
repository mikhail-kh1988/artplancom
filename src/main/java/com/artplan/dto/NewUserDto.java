package com.artplan.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class NewUserDto {

    @NotBlank
    private String login;

    @NotBlank
    private String password;
}
