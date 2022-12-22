package com.invictoprojects.marketplace.service.impl.storage

import com.invictoprojects.marketplace.config.MinIOConfig
import com.invictoprojects.marketplace.persistence.model.user.UserInformation
import com.invictoprojects.marketplace.persistence.model.user.extended.Nft
import com.invictoprojects.marketplace.service.StorageService
import com.invictoprojects.marketplace.service.impl.user.UserInformationServiceImpl
import com.invictoprojects.marketplace.service.impl.user.UserService
import io.minio.*
import io.minio.http.Method
import org.apache.commons.io.FilenameUtils
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile
import java.io.ByteArrayInputStream
import java.io.InputStream
import java.util.*
import java.util.function.Predicate


@Service
class NftStorageService (
    private val userService: UserService,
    private val userInformationImpl: UserInformationServiceImpl,
    private val minioClient: MinioClient = MinIOConfig().client()
) : StorageService {

    private final val bucketNameBanner = "users.nft"
    private final val expiryConstant = 7 * 24 * 60 * 60 // 7 days

    /**
     * Uploads a file to the MinIO server and names it with the entities id
     */
    override fun uploadObject(file: MultipartFile): InputStream {
        val tags = mutableMapOf<String, String>()
        tags["user_id"] = userService.getCurrentUser().id.toString()

        minioClient.putObject(PutObjectArgs.builder()
            .bucket(bucketNameBanner).`object`(userInformationImpl.addNft(file.contentType!!))
            .stream(file.inputStream, file.size, -1)
            .contentType(file.contentType)
            .tags(tags)
            .build())

        return getObject().get()
    }

    fun getAllByUserAndStatus(status: String) {
        userService.getCurrentUser().userInformation?.nfts?.stream()?.filter { obj: Nft -> obj.status.equals(status) }!!
    }

    fun getAllUrlsByUserAndStatus(status: String): ArrayList<String> {
        val urls: ArrayList<String> = ArrayList()
        userService.getCurrentUser().userInformation?.nfts?.stream()?.filter{
                obj: Nft -> obj.status.equals(status)
        }!!.forEach { nft: Nft ->
            urls.add(
                minioClient.getPresignedObjectUrl(
                    GetPresignedObjectUrlArgs.builder()
                        .method(Method.GET)
                        .bucket(bucketNameBanner)
                        .`object`(nft.id.toString() + "." + nft.file_type)
                        .expiry(expiryConstant)
                        .build()
                ))
        }

        return urls
    }

    fun getNftObject(bucketName: String, objectName: String): GetObjectResponse? {
        return minioClient.getObject(
            GetObjectArgs.builder().bucket(bucketNameBanner).`object`(getUserIdWithExtension()).build()
        )
    }

    override fun getObject(): Optional<InputStream> {
        val byteArray = minioClient.getObject(
                GetObjectArgs.builder().bucket(bucketNameBanner).`object`(getUserIdWithExtension()).build()).readAllBytes()

            val inStream: InputStream = ByteArrayInputStream(byteArray)
            return Optional.of(inStream)

        //ToDo: Make return default image
    }

    /**
     * Endpoint returns image url
     * ToDo: Make it return on all users uploads
     */
    override fun getUrl(): String {

        val url = minioClient.getPresignedObjectUrl(
            GetPresignedObjectUrlArgs.builder()
                .method(Method.GET)
                .bucket(bucketNameBanner)
                .`object`(getUserIdWithExtension())
                .expiry(expiryConstant)
                .build()
        )

        /*
        val usr = userService.getCurrentUser()
        usr.userInformation?.bannerUrl = url
        userService.updateInformation(usr)
         */

        return url
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
