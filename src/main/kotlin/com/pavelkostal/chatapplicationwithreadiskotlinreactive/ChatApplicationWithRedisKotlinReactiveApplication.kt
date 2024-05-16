package com.pavelkostal.chatapplicationwithreadiskotlinreactive

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class ChatApplicationWithRedisKotlinReactiveApplication

fun main(args: Array<String>) {
    runApplication<ChatApplicationWithRedisKotlinReactiveApplication>(*args)
}
