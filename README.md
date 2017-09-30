# Book Middle Service

Simple spring-boot spring-web API demonstrating creation of a REST Spring Boot middle service

The repo contains multiple branches, each focused on an implementation:

| Branch                 | Feature                                          |
| ---------------------- | ------------------------------------------------ |
| master                 | Add Spring-Web REST API                          |
| logging                | Configure log4j2 Logging                         |
| test-junit             | Add Unit and Integration Tests (Java/JUnit)      |
| test-e2e               | Add End-to-end API Tests (Javascript/Mocha/Chai) |
| configuration          | Add Spring Configuration and Profiles            |
| discovery-registration | Add Eureka Service Discovery                     |

## Build

`$ gradle build`

## Run

`$ gradle bootRun`

## Explore

Navigate to http://localhost:8080/books from a browser or Postman.