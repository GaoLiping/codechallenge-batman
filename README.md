# Code Challenge
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

# How to compile / run

To test locally.
```sh
mvn jetty:run
```

To package a war file for jetty.
```sh
mvn package war:war
```

To build a docker container and run it.
```sh
docker build -t "rws" .
docker run -d -p 8080:8080 rws
```


