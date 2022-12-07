package com.invictoprojects.marketplace.config

import com.auth0.jwt.JWT
import com.auth0.jwt.JWTVerifier
import com.auth0.jwt.algorithms.Algorithm
import com.auth0.jwt.exceptions.JWTCreationException
import com.auth0.jwt.exceptions.JWTVerificationException
import com.auth0.jwt.interfaces.DecodedJWT
import com.invictoprojects.marketplace.persistence.model.User
import com.invictoprojects.marketplace.service.impl.user.UserService
import org.springframework.beans.factory.annotation.Value
import org.springframework.core.env.Environment
import org.springframework.security.core.Authentication
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service
import java.security.interfaces.RSAPrivateKey
import java.security.interfaces.RSAPublicKey
import java.time.Instant
import javax.annotation.PostConstruct

@Service
class JwtProvider(
    val userService: UserService,
    var env: Environment


) {

    @Value("\${jwt.expiration.time}")
    val jwtExpirationInMillis: Long = 6000

    @Value("\${jwt.public.key}")
    var publicKey: RSAPublicKey? = null

    @Value("\${jwt.private.key}")
    var privateKey: RSAPrivateKey? = null

    fun jwtDecoder(token: String): DecodedJWT? {
        val algorithm: Algorithm = Algorithm.RSA256(publicKey, privateKey)

        val decodedJWT: DecodedJWT? = null
        try {
            val verifier: JWTVerifier = JWT.require(algorithm) // specify an specific claim validations
                .withIssuer("invicto") // reusable verifier instance
                .build()
            val decodedJWT = verifier.verify(token);
        } catch (_: JWTVerificationException) { }

        return decodedJWT
    }


    fun jwtEncoder(email: String): String {
        val algorithm: Algorithm = Algorithm.RSA256(publicKey, privateKey)

        val user: User = userService.findByEmail(email)
            ?: throw UsernameNotFoundException("User name with email $email")

        try {
            return JWT.create()
                .withIssuer("invicto")
                .withSubject(user.username)
                .withClaim("verified", user.enabled)
                .withClaim("user_id", user.id)
                .withClaim("scope", user.role.name)
                .withAudience("user")
                .withExpiresAt(Instant.now().plusSeconds(129600))
                .withIssuedAt(Instant.now())
                .sign(algorithm)
        } catch (exception: JWTCreationException) {
            // Invalid Signing configuration / Couldn't convert Claims.
        }

        throw Exception("JWT creation failed")
    }

    fun generateToken(authentication: Authentication): String {
        return jwtEncoder(authentication.name)
    }

    //ToDo: Check if this can be replaced, and how it correlates with session storage
    fun generateTokenWithEmail(email: String): String {
        return jwtEncoder(email)
    }


}
