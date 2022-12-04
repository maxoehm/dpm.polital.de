package com.invictoprojects.marketplace.service.impl.storage

import com.invictoprojects.marketplace.config.MinIOConfig
import com.invictoprojects.marketplace.service.StorageService
import com.invictoprojects.marketplace.service.impl.user.UserService
import io.minio.*
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile

@Service
class BannerStorageService (
    private val userService: UserService,
    private val minioClient: MinioClient = MinIOConfig().client()
) : StorageService {

    private final val bucketNameBanner = "users.banners"

    override fun uploadObject(file: MultipartFile) {
        minioClient.putObject(
            PutObjectArgs.builder()
                .bucket(bucketNameBanner).
                `object`(getObjectName())
                .stream(file.inputStream, file.size, -1)
                .contentType(file.contentType)
                .build()
        )
    }

    override fun getBannerObject(bucketName: String, objectName: String): GetObjectResponse? {
        return minioClient.getObject(
            GetObjectArgs.builder().bucket(bucketNameBanner).`object`(getObjectName()).build()
        )
    }

    override fun removeObject(bucketName: String, objectName: String) {
        return minioClient.removeObject(
            RemoveObjectArgs.builder().bucket(bucketNameBanner).`object`(getObjectName()).build()
        )
    }

    override fun getObjectName(): String {
        return userService.getCurrentUser().userInformation?.userInformationId.toString()
    }

}