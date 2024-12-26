package spring.apps.contactsApp.repository.impl;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.ArgumentPreparedStatementSetter;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapperResultSetExtractor;
import org.springframework.stereotype.Repository;
import spring.apps.contactsApp.mapper.ContactRowMapper;
import spring.apps.contactsApp.model.Contact;
import spring.apps.contactsApp.repository.ContactRepository;

@Repository
@Primary
@Slf4j
@RequiredArgsConstructor
public class ContactRepositoryImpl implements ContactRepository {
  private final JdbcTemplate jdbcTemplate;

  @Override
  public Collection<Contact> findAll() {
    log.debug("Calling ContactRepositoryImpl -> findAll.");
    String sql = "SELECT * FROM contacts";
    return jdbcTemplate.query(sql, new ContactRowMapper());
  }

  @Override
  public Optional<Contact> findById(Long id) {
    log.debug("Calling ContactRepositoryImpl -> findById. ID is {}.", id);
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
    log.debug("Calling ContactRepositoryImpl -> save. ID is {}.", contact.getId());
    String sql =
        "INSERT INTO contacts (id, first_name, last_name, email, phone) VALUES (?, ?, ?, ?, ?)";
    jdbcTemplate.update(
        sql,
        contact.getId(),
        contact.getFirstName(),
        contact.getLastName(),
        contact.getEmail(),
        contact.getPhone());
    return contact;
  }

  @Override
  public Contact update(Contact contact) {
    log.debug("Calling ContactRepositoryImpl -> update. ID is {}.", contact.getId());
    String sql =
        "UPDATE contacts c SET c.first_name = ?, c.last_name = ?, c.email = ?, c.phone = ? WHERE c.id = ?";
    Optional<Contact> optionalContact = findById(contact.getId());
    if (optionalContact.isPresent()) {
      jdbcTemplate.update(
          sql,
          contact.getFirstName(),
          contact.getLastName(),
          contact.getEmail(),
          contact.getPhone(),
          contact.getId());
      return contact;
    }
    log.warn("Can`t update contact with ID: {}, because contact doesn`t exist.", contact.getId());
    return null;
  }

  @Override
  public void delete(Contact contact) {
    log.debug("Calling ContactRepositoryImpl -> delete. ID is {}.", contact.getId());
    String sql = "DELETE FROM contacts c WHERE c.id = ?";
    Optional<Contact> optionalContact = findById(contact.getId());
    if (optionalContact.isPresent()) {
      jdbcTemplate.update(sql, contact.getId());
    }
    log.warn("Can`t delete contact with ID: {}, because contact doesn`t exist.", contact.getId());
  }

  @Override
  public void batchInsert(List<Contact> contacts) {
    log.debug("Calling ContactRepositoryImpl -> delete.");
    String sql =
        "INSERT INTO contacts c (c.id, c.first_name, c.last_name, c.email, c.phone) VALUES (?, ?, ?, ?, ?)";
    jdbcTemplate.batchUpdate(
        sql,
        new BatchPreparedStatementSetter() {
          @Override
          public void setValues(PreparedStatement ps, int i) throws SQLException {
            Contact contact = contacts.get(i);
            ps.setLong(1, contact.getId());
            ps.setString(2, contact.getFirstName());
            ps.setString(3, contact.getLastName());
            ps.setString(4, contact.getEmail());
            ps.setString(5, contact.getPhone());
          }

          @Override
          public int getBatchSize() {
            return contacts.size();
          }
        });
  }
}
