package com.example.newAddressBook.service;

import com.example.newAddressBook.dto.ContactDTO;
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

    private final List<Contact> contactList = new ArrayList<>(); // In-memory storage
    private final AtomicLong idCounter = new AtomicLong(1); // Generate unique IDs

    // Add a new Contact
    public Contact addContact(ContactDTO contactDTO) {
        Contact contact = new Contact(idCounter.getAndIncrement(), contactDTO.getName(), contactDTO.getPhone(), contactDTO.getEmail(), contactDTO.getAddress());
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
        log.info("Fetching contact with ID: {}", id);
        return contactList.stream().filter(contact -> contact.getId().equals(id)).findFirst();
    }

    // Update Contact by ID
    public Contact updateContact(Long id, ContactDTO contactDTO) {
        Optional<Contact> contactOptional = getContactById(id);
        if (contactOptional.isPresent()) {
            Contact contact = contactOptional.get();
            contact.setName(contactDTO.getName());
            contact.setPhone(contactDTO.getPhone());
            contact.setEmail(contactDTO.getEmail());
            contact.setAddress(contactDTO.getAddress());
            log.info("Updated contact with ID: {}", id);
            return contact;
        }
        log.warn("Contact with ID {} not found for update", id);
        return null;
    }

    // Delete Contact by ID
    public boolean deleteContact(Long id) {
        boolean removed = contactList.removeIf(contact -> contact.getId().equals(id));
        if (removed) {
            log.info("Deleted contact with ID: {}", id);
        } else {
            log.warn("Contact with ID {} not found for deletion", id);
        }
        return removed;
    }
}
