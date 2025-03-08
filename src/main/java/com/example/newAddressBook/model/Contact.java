package com.example.newAddressBook.model;

import com.example.newAddressBook.dto.ContactDTO;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Contact {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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