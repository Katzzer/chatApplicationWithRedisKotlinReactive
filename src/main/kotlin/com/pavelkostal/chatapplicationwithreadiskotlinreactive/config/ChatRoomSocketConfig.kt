package com.pavelkostal.chatapplicationwithreadiskotlinreactive.config

import com.pavelkostal.chatapplicationwithreadiskotlinreactive.service.ChatRoomService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.HandlerMapping
import org.springframework.web.reactive.handler.SimpleUrlHandlerMapping
import org.springframework.web.reactive.socket.WebSocketHandler

@Configuration
class ChatRoomSocketConfig {
    @Autowired
    private val chatRoomService: ChatRoomService? = null

    @Bean
    fun handlerMapping(): HandlerMapping {
        val map: Map<String, WebSocketHandler?> = java.util.Map.of<String, WebSocketHandler?>(
            "/chat", chatRoomService
        )
        return SimpleUrlHandlerMapping(map, -1) // give the most priority
    }
}
