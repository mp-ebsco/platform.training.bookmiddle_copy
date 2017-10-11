# Book Middle Service

Simple spring-boot spring-web API demonstrating creation of a REST Spring Boot middle service

The repo contains multiple branches, each focused on a feature:

| Branch                                                                                                                        | Feature                                      |
| ----------------------------------------------------------------------------------------------------------------------------- | -------------------------------------------- |
| master                                                                                                                        | Spring-Web REST API                          |
| logging - [diff](https://github.com/mp-ebsco/platform.training.bookmiddle_epam/compare/logging)                               | Configure log4j2 Logging and Zipkin/Sleuth   | 
| test-junit - [diff](https://github.com/mp-ebsco/platform.training.bookmiddle_epam/compare/test-junit)                         | Unit and Integration Tests (Java/JUnit)      |
| test-e2e - [diff](https://github.com/mp-ebsco/platform.training.bookmiddle_epam/compare/test-e2e)                             | End-to-end API Tests (Javascript/Mocha/Chai) |
| configuration - [diff](https://github.com/mp-ebsco/platform.training.bookmiddle_epam/compare/configuration)                   | Spring Configuration and Profiles            |
| discovery-registration - [diff](https://github.com/mp-ebsco/platform.training.bookmiddle_epam/compare/discovery-registration) | Eureka Service Discovery                     |
| circuit-breaker - [diff](https://github.com/mp-ebsco/platform.training.bookmiddle_epam/compare/circuit-breaker)               | Hystrix Circuit Breaker                      |

## Build

`$ gradle build`

## Run

`$ gradle bootRun`

## Explore

Navigate to http://localhost:8080/books from a browser or Postman.