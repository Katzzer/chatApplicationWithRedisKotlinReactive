package com.pavelkostal.chatapplicationwithreadiskotlinreactive.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Mono

@RestController
@RequestMapping("test")
class BasicController {

    @GetMapping
    fun hello(): Mono<String> {
        return Mono.just("Hello for Heroku!!!")
    }
}