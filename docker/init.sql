CREATE SCHEMA IF NOT EXISTS advanced_contacts_schema;
CREATE TABLE IF NOT EXISTS advanced_contacts_schema.contacts
(
    id BIGINT PRIMARY KEY,
    first_name VARCHAR(255) NOT NULL,
    last_name VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL,
    phone VARCHAR(255) NOT NULL
);