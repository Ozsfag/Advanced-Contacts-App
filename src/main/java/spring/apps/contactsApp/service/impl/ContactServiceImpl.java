package spring.apps.contactsApp.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spring.apps.contactsApp.model.Contact;
import spring.apps.contactsApp.repository.ContactRepository;
import spring.apps.contactsApp.service.ContactService;

import java.util.ArrayList;
import java.util.Collection;

@Service
@Slf4j
@RequiredArgsConstructor
public class ContactServiceImpl implements ContactService {
  private final ContactRepository contactRepository;

  @Override
  public Collection<Contact> findAll() {
    return contactRepository.findAll();
  }

  @Override
  public Contact findById(Long id) {
    return contactRepository.findById(id).orElse(null);
  }

  @Override
  public Contact save(Contact contact) {
    return contactRepository.save(contact);
  }

  @Override
  public Contact update(Contact contact) {
    return contactRepository.update(contact);
  }

  @Override
  public void delete(Contact contact) {
    contactRepository.delete(contact);
  }

  @Override
  public void batchInsert(Collection<Contact> contacts) {
    contactRepository.batchInsert(new ArrayList<>(contacts));
  }
}
