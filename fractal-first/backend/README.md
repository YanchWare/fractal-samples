# AWS Service Instance Management

This project provides a Spring Boot application for managing AWS service instances and bindings using the AWS SDK for
Java. It includes both unit and integration tests to ensure the correctness of the implementation.

## Table of Contents

- [Prerequisites](#prerequisites)
- [Installation](#installation)
- [Running the Application](#running-the-application)
- [Testing](#testing)
    - [Unit Tests](#unit-tests)
    - [Integration Tests](#integration-tests)
- [Project Structure](#project-structure)
- [Contributing](#contributing)
- [License](#license)

## Prerequisites

- Java 17 or higher
- Gradle 8.8 or higher
- Docker (for running integration tests with Testcontainers)

## Installation

Clone the repository:

```sh
git clone https://github.com/amasiero/aws-service-instance-management.git
cd aws-service-instance-management
```

# Running the Application

To run the application locally, use the following command:

```sh
./gradlew bootRun
```

The application will start and be accessible at http://localhost:8080.

# Testing

## Unit Tests

To run the unit tests, use the following command:

```sh
./gradlew test
```

## Integration Tests

Integration tests use Testcontainers to spin up a Localstack container that simulates AWS services.

To run the integration tests, use the following command:

```sh
./gradlew integrationTest
```

Project Structure
The project structure is as follows:

```bash
aws-service-instance-management
├── src
│   ├── main
│   │   ├── java
│   │   │   └── me
│   │   │       └── amasiero
│   │   │           └── osb
│   │   │               ├── config
│   │   │               │   └── aws
│   │   │               │       ├── AwsConfig.java     # Main configuration class
│   │   │               │       └── AwsProperties.java # Configuration properties for AWS
│   │   │               └── service
│   │   │                   └── aws
│   │   │                       └── AwsInstanceService.java  # Service class for AWS operations
│   ├── test
│   │   ├── java
│   │   │   └── me
│   │   │       └── amasiero
│   │   │           └── osb
│   │   │               └── service
│   │   │                   └── aws
│   │   │                       └── unit
│   │   │                           └── AwsInstanceServiceTest.java  # Unit test class
│   │   │                       └── integration
│   │   │                           ├── AbstractIntegrationTest.java  # Base class for integration tests   
│   │   │                           └── AwsInstanceServiceIntegrationTest.java  # Integration test class
├── build.gradle.kts
└── README.md
```

# API Documentation

The application has the endpoints listed
in [here](https://github.com/openservicebrokerapi/servicebroker/blob/v2.15/spec.md#provisioning).

# Contributing

Contributions are welcome! Please fork the repository and submit a pull request with your changes.

# License

This project is licensed under the MIT License - see the LICENSE file for details.
