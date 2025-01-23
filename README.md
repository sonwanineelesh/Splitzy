# Splitzy

Splitzy is a Spring Boot application designed to simplify the process of splitting bills among friends, roommates, or groups. It allows users to send requests for bill approvals or denials, ensuring transparent and efficient expense management.

## Features

- **Bill Splitting**: Easily divide expenses among multiple users.
- **Request Management**: Send and manage approval or denial requests for shared bills.
- **User Notifications**: Notify users of pending requests and updates.

## Project Structure

The project follows a standard Spring Boot structure:

- `src/main/java/com/example/demo/`
  - `DemoApplication.java`: The main entry point of the application.
  - `controller/`: Contains REST controllers handling HTTP requests.
  - `model/`: Defines data models or entities.
  - `repository/`: Interfaces extending JPA repositories for database interactions.
  - `service/`: Contains business logic of the application.

- `src/main/resources/`
  - `application.properties`: Configuration file for setting application properties.

## Getting Started

### Prerequisites

- **Java Development Kit (JDK)**: Ensure JDK 8 or higher is installed.
- **Maven**: For building and managing the project dependencies.
- **Git**: To clone the repository.

### Installation

1. **Clone the Repository**:

   ```bash
   git clone https://github.com/sonwanineelesh/Splitzy.git

