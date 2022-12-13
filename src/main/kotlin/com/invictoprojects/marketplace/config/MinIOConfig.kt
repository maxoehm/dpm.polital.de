package com.invictoprojects.marketplace.config

import io.minio.BucketExistsArgs
import io.minio.MakeBucketArgs
import io.minio.MinioClient
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Lazy
import org.springframework.http.MediaType
import org.springframework.http.converter.ByteArrayHttpMessageConverter
import org.springframework.http.converter.HttpMessageConverter
import java.nio.file.*
import java.util.function.Predicate
import javax.annotation.PostConstruct


@Configuration
class MinIOConfig {

    @Value("\${minio.endpoint}")
    var endpoint: String? = null

    @Value("\${minio.access.key}")
    var accessKey: String? = null

    @Value("\${minio.access.secret.key}")
    var secretKey: String? = null

    final val buckets = listOf("users.profile.image", "users.banners", "users.nft")

    @Bean
    fun client(): MinioClient {
        return MinioClient.builder()
            .endpoint(endpoint)
            .credentials(accessKey, secretKey)
            .build()
    }

    @Bean
    fun encryptionKey() { }

}