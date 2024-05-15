package com.pavelkostal.chatapplicationwithreadiskotlinreactive

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories

@SpringBootApplication
@EnableReactiveMongoRepositories
class ChatApplicationWithRedisKotlinReactiveApplication

fun main(args: Array<String>) {
    runApplication<ChatApplicationWithRedisKotlinReactiveApplication>(*args)
}
