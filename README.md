# Advanced Contacts App

The Advanced Contacts App is a Spring Boot application designed to manage contact information. It provides functionality
to create, edit, delete, and list contacts, utilizing a structured architecture with controllers, services, and
repositories.

## Features

- **Create Contacts**: Add new contacts with details such as first name, last name, email, and phone number.
- **Edit Contacts**: Modify existing contact information.
- **Delete Contacts**: Remove contacts from the database.
- **List Contacts**: View all contacts in the system.
- **Batch Insert**: Efficiently insert multiple contacts at once.

## Project Structure

- **ContactsAppApplication**: The main entry point of the application.
- **ContactController**: Manages HTTP requests and responses for contact operations, using annotations like
  `@GetMapping` and `@PostMapping`.
- **ContactService**: Interface defining business logic operations for managing contacts.
- **ContactServiceImpl**: Implements business logic and delegates database operations to the repository, using
  `@Service` and `@Slf4j`.
- **ContactRepository**: Interface defining CRUD operations for contacts.
- **ContactRepositoryImpl**: Implements database interactions using `JdbcTemplate`, with logging for traceability.

## Setup Instructions

1. **Prerequisites**:
    - Java 11 or higher
    - Gradle
    - A running instance of a relational database (e.g., MySQL, PostgreSQL)

2. **Clone the Repository**:
   ```bash
   git clone https://github.com/yourusername/Advanced-Contacts-App.git
   cd Advanced-Contacts-App