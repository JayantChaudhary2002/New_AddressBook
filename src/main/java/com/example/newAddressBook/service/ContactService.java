package com.example.newAddressBook.service;

import com.example.newAddressBook.dto.ContactDTO;
import com.example.newAddressBook.exception.AddressBookNotFoundException;
import com.example.newAddressBook.model.Contact;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

@Slf4j
@Service
public class ContactService {

    private final List<Contact> contactList = new ArrayList<>();
    private final AtomicLong idCounter = new AtomicLong(1);

    // Add a new Contact
    public Contact addContact(ContactDTO contactDTO) {
        Contact contact = new Contact(
                idCounter.getAndIncrement(),
                contactDTO.getName(),
                contactDTO.getPhone(),
                contactDTO.getEmail(),
                contactDTO.getAddress()
        );
        contactList.add(contact);
        log.info("Added new contact: {}", contact);
        return contact;
    }

    // Get all Contacts
    public List<Contact> getAllContacts() {
        log.info("Fetching all contacts. Total count: {}", contactList.size());
        return contactList;
    }

    // Get Contact by ID
    public Optional<Contact> getContactById(Long id) {
        return contactList.stream()
                .filter(contact -> contact.getId().equals(id))
                .findFirst();
    }

    // Update Contact by ID
    public Contact updateContact(Long id, ContactDTO contactDTO) {
        Contact contact = getContactById(id) // Ensure this returns an Optional<Contact>
                .orElseThrow(() -> new AddressBookNotFoundException(id));

        contact.setName(contactDTO.getName());
        contact.setPhone(contactDTO.getPhone());
        contact.setEmail(contactDTO.getEmail());
        contact.setAddress(contactDTO.getAddress());

        log.info("Updated contact with ID: {}", id);
        return contact;
    }


    // Delete Contact by ID
    public void deleteContact(Long id) {
        Contact contact = getContactById(id)
                .orElseThrow(() -> new AddressBookNotFoundException(id));
        contactList.remove(contact);
        log.info("Deleted contact with ID: {}", id);
    }
}
