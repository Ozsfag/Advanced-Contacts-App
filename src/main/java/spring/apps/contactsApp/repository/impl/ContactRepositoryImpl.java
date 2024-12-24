package spring.apps.contactsApp.repository.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.ArgumentPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapperResultSetExtractor;
import org.springframework.stereotype.Repository;
import spring.apps.contactsApp.mapper.ContactRowMapper;
import spring.apps.contactsApp.model.Contact;
import spring.apps.contactsApp.repository.ContactRepository;

import java.util.Collection;
import java.util.Optional;

@Repository
@Primary
@Slf4j
public class ContactRepositoryImpl implements ContactRepository {
  @Autowired private JdbcTemplate jdbcTemplate;

  @Override
  public Collection<Contact> findAll() {
    log.debug("Calling ContactRepositoryImpl -> findAll");
    String sql = "SELECT * FROM contacts";
    return jdbcTemplate.query(sql, new ContactRowMapper());
  }

  @Override
  public Optional<Contact> findById(Long id) {
    log.debug("Calling ContactRepositoryImpl -> findById. ID is {}", id);
    String sql = "SELECT * FROM contacts c WHERE c.id = ?";
    Contact contact =
        DataAccessUtils.singleResult(
            jdbcTemplate.query(
                sql,
                new ArgumentPreparedStatementSetter(new Object[] {id}),
                new RowMapperResultSetExtractor<>(new ContactRowMapper(), 1)));
    return Optional.ofNullable(contact);
  }

  @Override
  public Contact save(Contact contact) {
      log.debug("Calling ContactRepositoryImpl -> save. ID is {}", contact.getId());
      String sql = "INSERT INTO contacts (id, first_name, last_name, email, phone) VALUES (?, ?, ?, ?, ?)";
      jdbcTemplate.update(sql, contact.getId(), contact.getFirstName(), contact.getLastName(), contact.getEmail(), contact.getPhone());
      return contact;
  }

  @Override
  public Contact update(Contact contact) {
      log.debug("Calling ContactRepositoryImpl -> update. ID is {}", contact.getId());
    return null;
  }

  @Override
  public void delete(Contact contact) {}

  @Override
  public void batchInsert(Collection<Contact> contacts) {}
}
