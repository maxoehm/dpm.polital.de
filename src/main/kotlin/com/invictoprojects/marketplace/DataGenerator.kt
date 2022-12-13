package com.invictoprojects.marketplace

import com.invictoprojects.marketplace.config.MinIOConfig
import com.invictoprojects.marketplace.persistence.repository.UserRepository
import io.minio.BucketExistsArgs
import io.minio.MakeBucketArgs
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.boot.CommandLineRunner
import org.springframework.context.annotation.Bean
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Component


@Component
class DataGenerator(
    private val minIOConfig: MinIOConfig
) {

    private final val logger: Logger = LoggerFactory.getLogger(javaClass)

    @Bean
    fun loadData(passwordEncoder: PasswordEncoder?, userRepository: UserRepository): CommandLineRunner? {
        return CommandLineRunner {
            minioSetup()
            return@CommandLineRunner
        }
    }

    private fun minioSetup() {
        logger.info("Checking for existing buckets")
        minIOConfig.buckets.forEach() {
            if (!minIOConfig.client().bucketExists(BucketExistsArgs.builder().bucket(it).build())) {
                logger.info("Creating bucket: $it")
                minIOConfig.client().makeBucket(MakeBucketArgs.builder().bucket(it).build())
            }
        }
        logger.info("MinIO setup successful")
    }

}