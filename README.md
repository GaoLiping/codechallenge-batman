# Code Challenge

## First iteration
The goal of the challenge is to create a dockerized web service such that the following HTTP request, for the variable `$name`
```sh
curl -X POST http://localhost:8080/messages/names/$name
```
produces a response with header Content-Type: application/json and JSON payload:
```json
{"message": { "content": "Hello $name"}}
```
Furthermore, the service must meet the following requirements:
* Code must be stored on github and be in a fork of this repository
* Code must be written in java
* Maven is used to build the project and produce a war file
* Jetty is used as the web server
* The REST interface is produced by Jersey
* It must have a class `MessageResource` containing the REST API definition and a `MessageService` class, that performs the logic.
* It must use Spring for injecting the singleton `MessageService` bean into the `MessageResource` bean
* JAX RS / Jackson is used for serializing a `Message` DTO class into the REST response.
* The project must be built as a docker image using a Dockerfile starting with `FROM ubuntu:trusty`, and run as a docker container.
* You have to write every line of code yourself, but you can ask anyone for help. No-one is required to help though.

## Second iteration
The second iteration consists adding a new REST resource to the project, such that the following curl:
```
curl -X GET http://localhost:8080/messages/recent
```
produces the JSON output:
```json
{"messageCount": 2,
 "lastMessage": "2012-04-23T18:25:43.511Z",
 "messages": [
   {"message": { "content": "hello $name1"}},
   {"message": { "content": "hello $name2"}}
 ]
}
```
The following requirements also have to be met:
* `messageCount` is the number of messages in the messages array
* The messages array is the list of the 10 (or less) most recent messages being produced by the POST resource `/messages/names/$name`
* The `lastMessage` field contains the timestamp of the last message that was produced.
* The messages are stored in a postgres database
* The database schema is initialized during the bootup of the project. The database and user can be created manually.
* The database is accessed using Spring JDBC
* There is a `MessagesDAO` class that handles the interaction with the database.
* The project build runs JUnit tests using Mockito for mock objects producing over 60% line coverage measured using Jacoco.

##Final iteration
Based on the project created in the first two iterations, create a frontend for the messages resource that serves up a GUI on `http://docker_host_ip:8080/`. 
The GUI has to be fulfil the following requirements:
* Must use angularjs
* Static HTML and JS files have to be served up from the jetty instance
* Must be able to create new messages using the `POST` resource
* Has to have a graphical interface that was designed by you
* Is able to list the previous messages using the `GET` resource.
* Uses `AJAX` calls for all the `REST` operations.

For the pull request, a demo video must be produced that shows the functionality of the UI.

# How to compile / run

To run locally.
```sh
mvn jetty:run
```

To package a war file for jetty, and embed it into a docker container named "rws".
```sh
mvn package
```

To run the docker container.
```sh
docker run -d -p 8080:8080 rws
```

# Testing notes

Run PostgreSQL in a docker container (https://registry.hub.docker.com/_/postgres/).
This exposes port 5432, user postgres:password and has database postgres already.
```sh
docker run --name dev-postgres -e POSTGRES_PASSWORD=password -p 5432:5432 -d postgres
```

Link PostgreSQL and webapp. Webapp can connect with user/port as specified above.
```sh
docker run -p 8080:8080 --link dev-postgres:postgres -d rws
```

Run jUnit tests, create jacoco report in target/site/jacoco/index.html
```sh
mvn test
mvn jacoco:report
```


