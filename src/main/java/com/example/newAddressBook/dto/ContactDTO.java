package com.example.newAddressBook.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data //Allows to generate auto setters and getters
@NoArgsConstructor
@AllArgsConstructor
public class ContactDTO {
    private String name;
    private String phone;
    private String email;
    private String address;
}