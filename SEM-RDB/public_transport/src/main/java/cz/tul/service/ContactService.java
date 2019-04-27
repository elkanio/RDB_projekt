package cz.tul.service;

import cz.tul.data.Contact;
import cz.tul.repositories.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class ContactService {

    @Autowired
    private ContactRepository contactRepository;

    public void create(Contact contact) {
        contactRepository.save(contact);
    }

    public boolean exists(String username) {
        return contactRepository.exists(username);
    }

    public List<Contact> getAllContacts() {
        return StreamSupport.stream(contactRepository.findAll().spliterator(), false).collect(Collectors.toList());
    }

    public void deleteContacts() {
        contactRepository.deleteAll();
    }
}
