package com.pavelkostal.chatapplicationwithreadiskotlinreactive.security

import com.pavelkostal.chatapplicationwithreadiskotlinreactive.repository.FakeUserRepository
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.ReactiveUserDetailsService
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono

@Service
class CustomReactiveUserDetailsService(
    private val userRepository: FakeUserRepository,
) : ReactiveUserDetailsService {

    override fun findByUsername(username: String): Mono<UserDetails> {
        return userRepository.findByUsername(username)
            .map { user ->
                org.springframework.security.core.userdetails.User
                    .withUsername(user.username)
                    .password(user.password)
                    .authorities(user.getRoles().map { SimpleGrantedAuthority(it) })
                    .build()
            }
            .switchIfEmpty(Mono.error(UsernameNotFoundException("User not found with username: $username")))
    }
}