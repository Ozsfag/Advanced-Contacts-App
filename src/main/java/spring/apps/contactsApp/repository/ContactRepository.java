package spring.apps.contactsApp.repository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;
import spring.apps.contactsApp.model.Contact;

@Repository
public interface ContactRepository {
  Collection<Contact> findAll();

  Optional<Contact> findById(Long id);

  Contact save(Contact contact);

  Contact update(Contact contact);

  void delete(Contact contact);

  void batchInsert(List<Contact> contacts);
}
