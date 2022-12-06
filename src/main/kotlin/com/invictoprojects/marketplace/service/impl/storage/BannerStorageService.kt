package com.invictoprojects.marketplace.service.impl.storage

import com.invictoprojects.marketplace.config.MinIOConfig
import com.invictoprojects.marketplace.service.StorageService
import com.invictoprojects.marketplace.service.impl.user.UserService
import io.minio.*
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile
import java.io.File

@Service
class BannerStorageService (
    private val userService: UserService,
    private val minioClient: MinioClient = MinIOConfig().client()
) : StorageService {

    private final val bucketNameBanner = "users.banners"

    override fun uploadObject(file: MultipartFile) {

       if (getUserBannerObject() != null) {
           removeObject()
       }

        minioClient.putObject(
            PutObjectArgs.builder()
                .bucket(bucketNameBanner).
                `object`(buildName(file))
                .stream(file.inputStream, file.size, -1)
                .contentType(file.contentType)
                .build()
        )
    }

    override fun buildName(file: MultipartFile): String {
        return getUserId() + "." + file.contentType!!
    }

    override fun getBannerObject(bucketName: String, objectName: String): GetObjectResponse? {
        return minioClient.getObject(
            GetObjectArgs.builder().bucket(bucketNameBanner).`object`(getUserId()).build()
        )
    }

    override fun getUserBannerObject(): GetObjectResponse? {
        return minioClient.getObject(
            GetObjectArgs.builder().bucket(bucketNameBanner).`object`(getUserId()).build()
        )
    }

    override fun removeObject() {
        return minioClient.removeObject(
            RemoveObjectArgs.builder().bucket(bucketNameBanner).`object`(getUserId()).build()
        )
    }

    override fun getUserId(): String {
        return userService.getCurrentUser().userInformation?.userInformationId.toString()
    }

}
