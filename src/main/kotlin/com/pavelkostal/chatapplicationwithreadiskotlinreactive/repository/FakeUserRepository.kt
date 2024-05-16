package com.pavelkostal.chatapplicationwithreadiskotlinreactive.repository

import com.pavelkostal.chatapplicationwithreadiskotlinreactive.entity.User
import org.reactivestreams.Publisher
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Repository
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@Repository
class FakeUserRepository : ReactiveCrudRepository<User, String> {
    private final val encoder = BCryptPasswordEncoder()

    private val john = User("1", "john", encoder.encode("john"), setOf("ROLE_USER"))
    private val jack = User("2", "jack", encoder.encode("jack"), setOf("ROLE_USER"))
    private var userList = mutableListOf(john, jack)

    fun findByUsername(username: String): Mono<User> = Mono.justOrEmpty(userList.find { it.username == username })

    override fun findById(id: String): Mono<User> = Mono.justOrEmpty(userList.find { it.getId() == id })

    override fun findAll(): Flux<User> = Flux.fromIterable(userList)

    override fun deleteById(id: String): Mono<Void> {
        userList.removeIf { it.getId() == id}
        return Mono.empty()
    }

    override fun deleteAll(entities: Iterable<User>): Mono<Void> {
        entities.forEach { entity -> userList.removeIf { it.getId() == entity.getId() } }
        return Mono.empty()
    }

    override fun existsById(id: String): Mono<Boolean> = Mono.just(userList.any { it.getId() == id })

    override fun count(): Mono<Long> = Mono.just(userList.size.toLong())

    override fun findAllById(ids: Iterable<String>): Flux<User> = Flux.fromIterable(userList.filter { ids.contains(it.getId()) })

    override fun delete(entity: User): Mono<Void> {
        userList.removeIf { it.getId() == entity.getId()}
        return Mono.empty()
    }

    override fun deleteAll(): Mono<Void> {
        userList.clear()
        return Mono.empty()
    }

    override fun deleteById(id: Publisher<String>): Mono<Void> {
        return Mono.from(id).doOnNext { idStr -> userList.removeIf { it.getId() == idStr } }.then()
    }

    override fun deleteAllById(ids: Iterable<String>): Mono<Void> {
        ids.forEach { id -> userList.removeIf { it.getId() == id } }
        return Mono.empty()
    }

    override fun deleteAll(entityStream: Publisher<out User>): Mono<Void> = throw RuntimeException("Not implemented")  // Does nothing for now

    override fun findById(id: Publisher<String>): Mono<User> {
        return Mono.from(id).flatMap { idStr -> Mono.justOrEmpty(userList.find { it.getId() == idStr }) }
    }

    override fun existsById(id: Publisher<String>): Mono<Boolean> = throw RuntimeException("Not implemented")  // Does nothing for now

    override fun findAllById(idStream: Publisher<String>): Flux<User> = throw RuntimeException("Not implemented")  // Does nothing for now

    override fun <S : User?> save(entity: S & Any): Mono<S> {
       userList.add(entity)
       return Mono.empty()
    }

    override fun <S : User?> saveAll(entities: Iterable<S>): Flux<S> = throw RuntimeException("Not implemented")  // Does nothing for now

    override fun <S : User?> saveAll(entityStream: Publisher<S>): Flux<S> = throw RuntimeException("Not implemented") // Does nothing for now
}
