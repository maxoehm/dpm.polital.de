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
import org.springframework.security.core.Authentication
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.security.oauth2.jwt.JwtClaimsSet
import org.springframework.security.oauth2.jwt.JwtEncoder
import org.springframework.security.oauth2.jwt.JwtEncoderParameters
import org.springframework.stereotype.Service
import java.lang.Exception
import java.security.interfaces.RSAPrivateKey
import java.security.interfaces.RSAPublicKey
import java.time.Instant

@Service
class JwtProvider(
    @Value("\${jwt.expiration.time}") val jwtExpirationInMillis: Long,
    val userService: UserService
) {

    @Value("\${jwt.public.key}")
    var publicKey: RSAPublicKey? = null

    @Value("\${jwt.private.key}")
    var privateKey: RSAPrivateKey? = null

    private final val algorithm: Algorithm = Algorithm.RSA256(publicKey, privateKey)

    fun jwtDecoder(token: String): DecodedJWT? {
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

        val user = userService.findByEmail(email)
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
        val principal = authentication.principal as User
        return jwtEncoder(principal.username)
    }

}
