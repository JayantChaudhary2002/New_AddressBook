package com.example.newAddressBook.exception;

public class AddressBookNotFoundException extends RuntimeException {
    public AddressBookNotFoundException(Long id) {
        super("Address Book entry with ID " + id + " not found.");
    }
}
