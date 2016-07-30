# A step-by-step POC project for building Spring way of Monolithic App

This project is not a continuous one in the classical sense. Every commit represents a specific step and implements a new requirement as the code continues to grow.

> Spring technology stack and dependencies

- Spring Boot 1.4.0.RELEASE

## Pre-requisites

- Java 1.8+
- Maven
- Spring STS IDE

## The individual steps in detail

The domain model is a tiny CRM system in the first place, but can be extended into an e-commerce system later on. Core abstractions are `Customer`s that have an `EmailAddress` as well as `Address`es, `Product`s and `Order`s carrying `LineItem`s placed by `Customers`. 

Firstly step by step POC will be implemented for Customer, and then similar flow will be implemented for remaining Product, LineItem and Order domain models.

### Step 0 - Plain vanilla project setup

__Objective:__ Learn how to quickly create a new Maven based project with Spring Boot using STS IDE (or Spring Initialzr).

> How to create a Spring Boot project quickly?

- Use `[Spring Initialzr](https://start.spring.io/)` or `STS -> File -> New -> Spring Starter Project`
- Create Spring Boot project without selecting any dependencies
- Rename auto-generated class with simplified name 'Application.java' and 'ApplicationTest.java'
- Should be able to run unit test successfully



## Disclaimer

Inspired by [Spring Data JPA repositories deep dive talk](https://github.com/olivergierke/repositories-deepdive), with a similar approach of unit wise logical Git commits, I thought to create step-by-step POC project for building Spring way of Monolithic App.