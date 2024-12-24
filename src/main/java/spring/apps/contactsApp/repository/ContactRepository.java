package spring.apps.contactsApp.repository;

import spring.apps.contactsApp.model.Contact;

import java.util.Collection;
import java.util.Optional;

public interface ContactRepository {
  Collection<Contact> findAll();

  Optional<Contact> findById(Long id);

  Contact save(Contact contact);

  Contact update(Contact contact);

  void delete(Contact contact);

  void batchInsert(Collection<Contact> contacts);
}
