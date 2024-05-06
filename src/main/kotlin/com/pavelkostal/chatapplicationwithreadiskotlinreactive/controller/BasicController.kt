package com.pavelkostal.chatapplicationwithreadiskotlinreactive.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Mono

@RestController("test")
class BasicController {

    @GetMapping
    fun hello(): Mono<String> {
        return Mono.just("Hello for Heroku")
    }
}