package spring.apps.contactsApp.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
import spring.apps.contactsApp.model.Contact;

public class ContactRowMapper implements RowMapper<Contact> {
  @Override
  public Contact mapRow(ResultSet rs, int rowNum) throws SQLException {
    Contact contact = new Contact();
    contact.setId(rs.getLong(Contact.Fields.id));
    contact.setFirstName(rs.getString("first_name"));
    contact.setLastName(rs.getString("last_name"));
    contact.setEmail(rs.getString(Contact.Fields.email));
    contact.setPhone(rs.getString(Contact.Fields.phone));
    return contact;
  }
}
