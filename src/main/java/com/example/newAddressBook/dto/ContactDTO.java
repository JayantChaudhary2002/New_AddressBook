package com.example.newAddressBook.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data //Allows to generate auto setters and getters
@NoArgsConstructor
@AllArgsConstructor
public class ContactDTO {

    @NotBlank(message = "Name is required and cannot be empty")
    @Pattern(regexp = "^[A-Za-z ]{3,50}$", message = "Name must be between 3-50 characters and contain only letters and spaces")
    private String name;

    private String phone;
    private String email;
    private String address;
}