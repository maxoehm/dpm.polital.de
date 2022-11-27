package com.invictoprojects.marketplace.config


import com.auth0.jwt.JWT
import com.auth0.jwt.JWTVerifier
import com.auth0.jwt.algorithms.Algorithm
import com.auth0.jwt.exceptions.JWTCreationException
import com.auth0.jwt.exceptions.JWTVerificationException
import com.auth0.jwt.interfaces.Claim
import com.auth0.jwt.interfaces.DecodedJWT
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.config.annotation.web.configurers.ExceptionHandlingConfigurer
import org.springframework.security.config.annotation.web.configurers.SessionManagementConfigurer
import org.springframework.security.config.annotation.web.configurers.oauth2.server.resource.OAuth2ResourceServerConfigurer
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.oauth2.jwt.JwtValidationException
import org.springframework.security.oauth2.server.resource.web.BearerTokenAuthenticationEntryPoint
import org.springframework.security.oauth2.server.resource.web.access.BearerTokenAccessDeniedHandler
import java.security.interfaces.RSAPrivateKey
import java.security.interfaces.RSAPublicKey
import java.time.Instant


@Configuration
@EnableWebSecurity
class SecurityConfig : WebSecurityConfigurerAdapter() {

    @Autowired
    var userDetailsService: UserDetailsService? = null

    @Value("\${jwt.public.key}")
    var publicKey: RSAPublicKey? = null

    @Value("\${jwt.private.key}")
    var privateKey: RSAPrivateKey? = null

    @Bean
    @Throws(Exception::class)
    override fun authenticationManagerBean(): AuthenticationManager {
        return super.authenticationManagerBean()
    }

    @Throws(java.lang.Exception::class)
    override fun configure(httpSecurity: HttpSecurity) {
        httpSecurity.cors().and()
            .csrf().disable()
            .authorizeHttpRequests { authorize ->
                authorize
                    .antMatchers("/api/auth/**").permitAll()
                    .antMatchers(HttpMethod.GET, "/api/categories/**", "/api/products/**").permitAll()
                    .antMatchers("/api/products/*/review").permitAll()
                    .antMatchers("/api/users/disable/**").hasAuthority("SCOPE_ADMIN")
                    .antMatchers("/api/orders/**", "/api/users/**").hasAuthority("SCOPE_USER")
                    .antMatchers("/api/products/**").hasAnyAuthority("SCOPE_SELLER", "SCOPE_ADMIN")
                    .antMatchers("/api/categories/**").hasAuthority("SCOPE_ADMIN")
                    .antMatchers(
                        "/v2/api-docs",
                        "/configuration/ui",
                        "/swagger-resources/**",
                        "/configuration/security",
                        "/swagger-ui/**",
                        "/webjars/**"
                    )
                    .permitAll()
                    .anyRequest()
                    .authenticated()
            }
            .oauth2ResourceServer { obj: OAuth2ResourceServerConfigurer<HttpSecurity?> -> obj.jwt() }
            .sessionManagement { session: SessionManagementConfigurer<HttpSecurity?> ->
                session.sessionCreationPolicy(
                    SessionCreationPolicy.STATELESS
                )
            }
            .exceptionHandling { exceptions: ExceptionHandlingConfigurer<HttpSecurity?> ->
                exceptions
                    .authenticationEntryPoint(BearerTokenAuthenticationEntryPoint())
                    .accessDeniedHandler(BearerTokenAccessDeniedHandler())
            }
    }

    @Throws(java.lang.Exception::class)
    override fun configure(authenticationManagerBuilder: AuthenticationManagerBuilder) {
        authenticationManagerBuilder.userDetailsService(userDetailsService)
            .passwordEncoder(passwordEncoder())
    }

    private final val algorithm: Algorithm = Algorithm.RSA256(publicKey, privateKey)

    @Bean
    fun passwordEncoder(): PasswordEncoder {
        return BCryptPasswordEncoder()
    }


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


    fun jwtEncoder(username: String, verified: Boolean, userId: Long): String? {
        try {
            return JWT.create()
                .withIssuer("invicto")
                .withSubject(username)
                .withClaim("verified", verified)
                .withClaim("user_id", userId)
                .withAudience("user")
                .withExpiresAt(Instant.now().plusSeconds(129600))
                .withIssuedAt(Instant.now())
                .sign(algorithm)
        } catch (exception: JWTCreationException) {
            // Invalid Signing configuration / Couldn't convert Claims.
        }

        return null
    }
}
