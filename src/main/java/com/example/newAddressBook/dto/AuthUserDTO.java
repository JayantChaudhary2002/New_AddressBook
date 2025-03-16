package com.example.newAddressBook.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AuthUserDTO {
    private String email;
    private String password;
    private String name;
}

