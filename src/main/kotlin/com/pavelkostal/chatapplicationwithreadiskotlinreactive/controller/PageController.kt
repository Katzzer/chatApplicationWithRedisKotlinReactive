package com.pavelkostal.chatapplicationwithreadiskotlinreactive.controller

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.reactive.result.view.Rendering
import reactor.core.publisher.Mono
import java.security.Principal

@Controller
class PageController {

    @GetMapping("/login")
    fun login(@RequestParam(required = false) error: String?): Rendering {
        return Rendering.view("login")
            .modelAttribute("loginError", error != null)
            .build()
    }

    @GetMapping
    fun hello(principal: Mono<Principal>): Mono<Rendering> {
        return principal
            .map { it.name.replaceFirstChar { char -> char.uppercaseChar() }}
            .map { Rendering.view("index")
                .modelAttribute("username", it)
                .modelAttribute("defaultRoom", "test")
                .build()
            }
    }
}