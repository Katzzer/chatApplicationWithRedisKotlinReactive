package com.pavelkostal.chatapplicationwithreadiskotlinreactive.controller

import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@CrossOrigin("http://localhost:4200", "http://localhost:8081", "https://pavelkostal-superheroapi.herokuapp.com", "http://superheroapi.pavelkostal.com")
class HealthController {

    companion object {
        private val START_TIME = System.currentTimeMillis()
    }

    @GetMapping("/health")
    fun health(): Map<String, Any> {
        val uptimeSeconds = (System.currentTimeMillis() - START_TIME) / 1000

        val days = uptimeSeconds / 86400
        val hours = (uptimeSeconds % 86400) / 3600
        val minutes = (uptimeSeconds % 3600) / 60
        val seconds = uptimeSeconds % 60

        return linkedMapOf(
            "status" to "UP",
            "uptimeSeconds" to uptimeSeconds,
            "uptime" to "${days}d ${hours}h ${minutes}m ${seconds}s"
        )
    }
}
