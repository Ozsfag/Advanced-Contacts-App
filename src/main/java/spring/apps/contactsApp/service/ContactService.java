package spring.apps.contactsApp.service;

import java.util.Collection;
import spring.apps.contactsApp.model.Contact;

public interface ContactService {
  Collection<Contact> findAll();

  Contact findById(Long id);

  Contact save(Contact contact);

  Contact update(Contact contact);

  void delete(Contact contact);

  void batchInsert(Collection<Contact> contacts);
}
