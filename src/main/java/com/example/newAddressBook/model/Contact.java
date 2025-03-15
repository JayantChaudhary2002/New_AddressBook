package com.example.newAddressBook.model;

import com.example.newAddressBook.dto.ContactDTO;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Contact {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Name is required and cannot be empty")
    @Pattern(regexp = "^[A-Za-z ]{3,50}$", message = "Name must be between 3-50 characters and contain only letters and spaces")
    private String name;

    private String phone;
    private String email;
    private String address;

    public Contact(ContactDTO contactDTO) {
        this.name = contactDTO.getName();
        this.phone = contactDTO.getPhone();
        this.email = contactDTO.getEmail();
        this.address = contactDTO.getAddress();
    }
}