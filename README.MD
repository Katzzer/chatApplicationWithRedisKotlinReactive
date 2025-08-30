# Chat application with Redis written in Kotlin with Spring Boot

## Description
- This is a simple application that demonstrates a chat application using Redis, Spring Boot with reactive Kotlin, Thymeleaf and Redis on AWS (not on localhost)
- Application is hosted on Heroku

## How to use it
- On localhost run `docker-compose.yml` (on web it uses Redis on AWS) 
- Log is as `john` with password `john` or as `jack` with password `jack`
- Select room. Room `test` is default
- Start to chat with other people :-)

## How to run it
- Run docker-compose.yml

## Configuration of Redis server
- Redis runs on https://app.redislabs.com/
- To configure this app you need `SPRING_DATA_REDIS_HOST`, `SPRING_DATA_REDIS_PASSWORD` and `SPRING_DATA_REDIS_PORT`
