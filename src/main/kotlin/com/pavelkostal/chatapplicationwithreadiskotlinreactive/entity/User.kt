package com.pavelkostal.chatapplicationwithreadiskotlinreactive.entity

import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import java.util.*

class User (
    private val id: String= UUID.randomUUID().toString(),
    private val username: String,
    private val password: String,
    private val roles: Set<String>
) : UserDetails {
    override fun getAuthorities(): Collection<GrantedAuthority> {
        return roles.map { SimpleGrantedAuthority(it) }
    }

    override fun getPassword(): String {
        return password
    }

    fun getRoles() = roles
    fun getId() = id

    override fun getUsername(): String {
        return username
    }

    override fun isEnabled(): Boolean {
        return true
    }

    override fun isCredentialsNonExpired(): Boolean {
        return true
    }

    override fun isAccountNonExpired(): Boolean {
        return true
    }

    override fun isAccountNonLocked(): Boolean {
        return true
    }
}