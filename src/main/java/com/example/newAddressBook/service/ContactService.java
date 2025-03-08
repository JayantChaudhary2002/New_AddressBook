package com.example.newAddressBook.service;

import com.example.newAddressBook.model.Contact;
import com.example.newAddressBook.repository.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ContactService {

    @Autowired
    private ContactRepository repository;

    public List<Contact> getAllContacts() {
        return repository.findAll();
    }

    public Contact getContactById(Long id) {
        return repository.findById(id).orElse(null);
    }

    public Contact addContact(Contact contact) {
        return repository.save(contact);
    }

    public Contact updateContact(Long id, Contact updatedContact) {
        return repository.findById(id)
                .map(contact -> {
                    contact.setName(updatedContact.getName());
                    contact.setEmail(updatedContact.getEmail());
                    contact.setPhoneNumber(updatedContact.getPhoneNumber());
                    contact.setAddress(updatedContact.getAddress());
                    return repository.save(contact);
                }).orElse(null);
    }

    public void deleteContact(Long id) {
        repository.deleteById(id);
    }
}
