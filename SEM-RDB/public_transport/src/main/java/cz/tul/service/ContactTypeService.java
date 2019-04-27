package cz.tul.service;

import cz.tul.data.ContactType;
import cz.tul.repositories.ContactTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class ContactTypeService {

    @Autowired
    private ContactTypeRepository contactTypeRepository;

    public void create(ContactType contactType) {
        contactTypeRepository.save(contactType);
    }

    public boolean exists(String username) {
        return contactTypeRepository.exists(username);
    }

    public List<ContactType> getAllContactsTypes() {
        return StreamSupport.stream(contactTypeRepository.findAll().spliterator(), false).collect(Collectors.toList());
    }

    public void deleteContactsTypes() {
        contactTypeRepository.deleteAll();
    }
}
