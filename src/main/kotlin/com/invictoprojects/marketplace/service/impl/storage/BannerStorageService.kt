package com.invictoprojects.marketplace.service.impl.storage

import com.invictoprojects.marketplace.config.MinIOConfig
import com.invictoprojects.marketplace.service.StorageService
import com.invictoprojects.marketplace.service.impl.user.UserInformationServiceImpl
import com.invictoprojects.marketplace.service.impl.user.UserService
import io.minio.*
import io.minio.errors.ErrorResponseException
import org.apache.commons.io.FilenameUtils
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile
import java.io.ByteArrayInputStream
import java.io.InputStream
import java.util.*

@Service
class BannerStorageService (
    private val userService: UserService,
    private val userInformationImpl: UserInformationServiceImpl,
    private val minioClient: MinioClient = MinIOConfig().client()
) : StorageService {

    private final val bucketNameBanner = "users.banners"

    override fun uploadObject(file: MultipartFile): InputStream {
        userInformationImpl.setBanner(FilenameUtils.getExtension(file.originalFilename));

        minioClient.putObject(PutObjectArgs.builder()
            .bucket(bucketNameBanner).`object`(getUserIdWithExtension())
            .stream(file.inputStream, file.size, -1)
            .contentType(file.contentType)
            .build())

        return getUserBannerObject().get()
    }

    override fun getBannerObject(bucketName: String, objectName: String): GetObjectResponse? {
        return minioClient.getObject(
            GetObjectArgs.builder().bucket(bucketNameBanner).`object`(getUserIdWithExtension()).build()
        )
    }


    override fun getUserBannerObject(): Optional<InputStream> {
        var logger = LoggerFactory.getLogger(BannerStorageService::class.java)

        logger.info(getUserIdWithExtension())


        val byteArray = minioClient.getObject(
                GetObjectArgs.builder().bucket(bucketNameBanner).`object`(getUserIdWithExtension()).build()).readAllBytes()


            val inStream: InputStream = ByteArrayInputStream(byteArray)
            return Optional.of(inStream)

        //ToDo: Make return default image
    }

    override fun removeObject() {
        return minioClient.removeObject(
            RemoveObjectArgs.builder().bucket(bucketNameBanner).`object`(getUserIdWithExtension()).build()
        )
    }

    override fun getUserIdWithExtension(): String {
        return userService.getCurrentUser().userInformation?.userInformationId.toString() + "." + userService.getCurrentUser().userInformation?.banner
    }

    override fun hasBanner(): Boolean {
        return userService.getCurrentUser().userInformation?.banner != null
    }

}
