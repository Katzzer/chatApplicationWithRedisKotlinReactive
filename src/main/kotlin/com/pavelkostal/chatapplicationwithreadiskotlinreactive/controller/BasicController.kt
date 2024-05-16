package com.pavelkostal.chatapplicationwithreadiskotlinreactive.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Mono
import java.security.Principal

@RestController
@RequestMapping("test")
class BasicController {

    @GetMapping()
    fun hello(principal: Mono<Principal>): Mono<String> {
        return principal
            .map { it.name }
            .map { username -> "Hello for Heroku!!! User: $username" }
    }
}