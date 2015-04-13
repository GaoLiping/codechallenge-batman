# Code Challenge
The goal of the challenge is to create a dockerized web service such that the following HTTP request, for the variable `$name`
```sh
curl -X POST http://localhost:8080/messages/names/$name
```
produces a response with header Content-Type: application/json and JSON payload:
```json
{"content": { "content": "Hello $name"}}
```
Furthermore, the service must meet the following requirements:
* Code must be stored on github and be in a fork of this repository
* Code must be written in java
* Maven is used to build the project and produce a war file
* Jetty is used as the web server
* The REST interface is produced by Jersey
* It must have a class `app.MessageResource` containing the REST API definition and a `app.MessageService` class, that performs the logic.
* It must use Spring for injecting the singleton `app.MessageService` bean into the `app.MessageResource` bean
* JAX RS / Jackson is used for serializing a `app.Message` DTO class into the REST response.
* The project must be built as a docker image using a Dockerfile starting with `FROM ubuntu:trusty`, and run as a docker container.
* You have to write every line of code yourself, but you can ask anyone for help. No-one is required to help though.

