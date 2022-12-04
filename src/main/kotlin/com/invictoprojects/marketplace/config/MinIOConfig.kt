package com.invictoprojects.marketplace.config

import io.minio.MinioClient
import io.minio.ServerSideEncryption
import io.minio.ServerSideEncryptionCustomerKey
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import java.nio.file.*
import javax.crypto.KeyGenerator

@Configuration
class MinIOConfig {

    @Value("\${minio.endpoint}")
    var endpoint: String? = null

    @Value("\${minio.access.key}")
    var accessKey: String? = null

    @Value("\${minio.access.secret.key}")
    var secretKey: String? = null

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