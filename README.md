# Elevator

This project is to demonstrate a Maven project with multiple modules to represent
elevator controllers.

## Project

The project consists of three modules

* **Model:**
Model classes and logic to move and control elevators

* **API:**
A Java RESTfull API web application hosting elevators logic

* **UI:**
A React JS project with an example page to control elevators in a building


## What the project demonstrates
* Using OOP to represent real world objects and logic in the model module
* TDD using junit in model module to ensure intended logic is correctly implemented
* Microservice used to host business logic in the api module
* DDD using cucumber to test business logic at api level
* Briefly using beans/IOC to configure API service
* ReactJS is used to create user interface and interact with APIs.

## Further possible enhancements
* More DDD test cases to add more coverage
* DDD can be used to test UI as well through a framework like Selenium.
* For a more complicated model, IOC could be used more to separate concerns of contracts from implementation details
* Maven could be used to run both API service and React UI with single command for simplicity

## Assumptions made to simplify model
* When calling elevators from a floor, there is only a single button to call elevators not specifying if the passenger is going up or down.
* Elevators move together when the API 'tick' is called, which means no consideration is made in the model for an elevator transitioning between two floors.
Also, the actions of opening and closing the door is considered to be taking one tick, which means it is assumed to take same time as an elevator moving between one floor and the next.

## How to run the project
Assuming the following is installed on your Linux/WSL system:
* Java 8
* Maven
* Node
* Yarn
* Git \[Optional because you can download as a ZIP file\]

After cloning the project locally, and in the project root folder run the following
1. `mvn clean install`
2. `mvn spring-boot:run -pl com.anz.elevator.api`
3. Leave the terminal window running in step 2 and open a new window/tab
4. `cd com.anz.elevator.ui`
5. `yarn start`