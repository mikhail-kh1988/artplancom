package com.artplan.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PastOrPresent;
import java.time.LocalDate;

@Getter
@Setter
public class NewAnimalDto {
    @NotBlank
    private String name;

    @NotBlank
    private char sex;

    @PastOrPresent
    private LocalDate birthDay;

    @NotBlank
    private String typeAnimal;
}
