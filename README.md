# Waiter App

Restaurant management system MVP. Written in Java using Spring Boot.

## Requirements
* Java Development Kit 8 or higher.
* MySQL SGDB with a database called "waiter".
* Docker (Optional).

## Build
This project can be built using either Maven or Docker.

### Build with Maven

`mvn clean install`

### Build with Docker

`docker build -t waiter-app .`

## Run

### Java
`java -jar waiter-artifact-name.jar`

### Docker
`docker run -d -p 8080:8080 waiter-app`

