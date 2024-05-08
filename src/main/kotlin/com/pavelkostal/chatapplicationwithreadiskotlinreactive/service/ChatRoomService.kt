package com.pavelkostal.chatapplicationwithreadiskotlinreactive.service

import org.redisson.api.RListReactive
import org.redisson.api.RTopicReactive
import org.redisson.api.RedissonReactiveClient
import org.redisson.client.codec.StringCodec
import org.springframework.stereotype.Service
import org.springframework.web.reactive.socket.WebSocketHandler
import org.springframework.web.reactive.socket.WebSocketMessage
import org.springframework.web.reactive.socket.WebSocketSession
import org.springframework.web.util.UriComponentsBuilder
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import reactor.core.publisher.SignalType
import java.net.URI

@Service
class ChatRoomService(private val client: RedissonReactiveClient) : WebSocketHandler {

    override fun handle(webSocketSession: WebSocketSession): Mono<Void> {
        val room = getChatRoomName(webSocketSession)

        val topic: RTopicReactive = client.getTopic(room, StringCodec.INSTANCE)
        val list: RListReactive<String> = client.getList("history:$room", StringCodec.INSTANCE)

        // subscribe
        webSocketSession.receive()
            .map { obj: WebSocketMessage -> obj.payloadAsText }
            .flatMap { msg: String? -> list.add(msg).then(topic.publish(msg)) }
            .doOnError { x: Throwable? -> println(x) }
            .doFinally { s: SignalType -> println("Subscriber finally $s") }
            .subscribe()

        // publisher
        val flux: Flux<WebSocketMessage> = topic.getMessages(String::class.java)
            .startWith(list.iterator())
            .map { payload: String -> webSocketSession.textMessage(payload) }
            .doOnError(System.out::println)
            .doFinally { s -> println("Publisher finally $s") }

        return webSocketSession.send(flux)
    }

    private fun getChatRoomName(socketSession: WebSocketSession): String {
        val uri: URI = socketSession.getHandshakeInfo().getUri()
        return UriComponentsBuilder.fromUri(uri).build()
            .getQueryParams()
            .toSingleValueMap()
            .getOrDefault("room", "default")
    }
}
