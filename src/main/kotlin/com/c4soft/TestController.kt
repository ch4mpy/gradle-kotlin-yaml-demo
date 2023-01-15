package com.c4soft

import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class TestController {

    @GetMapping("/user")
    @PreAuthorize("hasRole('USER')")
    fun helloUser(): ResponseEntity<Foo> =  ResponseEntity.ok().body(Foo("Hello User"))

    @GetMapping("/admin")
    @PreAuthorize("hasRole('ADMIN')")
    fun helloAdmin(): ResponseEntity<Foo> =  ResponseEntity.ok().body(Foo("Hello Admin"))

    @GetMapping("/auth")
    fun auth(jwt : JwtAuthenticationToken) = jwt.authorities.map(GrantedAuthority::getAuthority);
}

data class Foo(val value: String)