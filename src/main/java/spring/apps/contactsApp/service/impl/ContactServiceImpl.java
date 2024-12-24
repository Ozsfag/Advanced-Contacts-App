package spring.apps.contactsApp.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spring.apps.contactsApp.model.Contact;
import spring.apps.contactsApp.repository.ContactRepository;
import spring.apps.contactsApp.service.ContactService;

import java.util.Collection;
import java.util.Optional;

@Service
@Slf4j
public class ContactServiceImpl implements ContactService {
    @Autowired private ContactRepository contactRepository;
    @Override
    public Collection findAll() {
        return contactRepository.findAll();
    }

    @Override
    public Optional<Contact> findById(Long id) {
        return contactRepository.;
    }

    @Override
    public Contact save(Contact contact) {
        return null;
    }

    @Override
    public Contact update(Contact contact) {
        return null;
    }

    @Override
    public void delete(Contact contact) {

    }
}
