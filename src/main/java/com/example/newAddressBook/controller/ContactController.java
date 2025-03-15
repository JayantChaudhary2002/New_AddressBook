package com.example.newAddressBook.controller;

import com.example.newAddressBook.dto.ContactDTO;
import com.example.newAddressBook.exception.AddressBookNotFoundException;
import com.example.newAddressBook.model.Contact;
import com.example.newAddressBook.service.ContactService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/contacts")
public class ContactController {

    private final ContactService contactService;

    public ContactController(ContactService contactService) {
        this.contactService = contactService;
    }

    // Create a new Contact
    @PostMapping
    public ResponseEntity<Contact> addContact(@Valid @RequestBody ContactDTO contactDTO) {
        Contact newContact = contactService.addContact(contactDTO);
        return ResponseEntity.ok(newContact);
    }

    // Get all Contacts
    @GetMapping
    public ResponseEntity<List<Contact>> getAllContacts() {
        return ResponseEntity.ok(contactService.getAllContacts());
    }

    // Get Contact by ID
    @GetMapping("/{id}")
    public ResponseEntity<Contact> getContactById(@PathVariable Long id) {
        Contact contact = contactService.getContactById(id)
                .orElseThrow(() -> new AddressBookNotFoundException(id));
        return ResponseEntity.ok(contact);
    }

    // Update Contact by ID
    @PutMapping("/{id}")
    public ResponseEntity<Contact> updateContact(@PathVariable Long id, @Valid @RequestBody ContactDTO contactDTO) {
        Contact updatedContact = contactService.updateContact(id, contactDTO);
        return ResponseEntity.ok(updatedContact);
    }

    // Delete Contact by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteContact(@PathVariable Long id) {
        contactService.deleteContact(id); // Will throw exception if not found
        return ResponseEntity.ok("Deleted Successfully");
    }
}
