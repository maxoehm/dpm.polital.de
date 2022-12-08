package com.invictoprojects.marketplace.service

import com.invictoprojects.marketplace.service.impl.storage.BannerStorageService
import io.minio.GetObjectResponse
import io.minio.UploadObjectArgs
import org.springframework.web.multipart.MultipartFile
import java.io.InputStream
import java.io.Serializable
import java.util.*

interface StorageService {

    fun getBannerObject(bucketName: String, objectName: String): GetObjectResponse?
    fun getUserBannerObject(): Optional<InputStream>
    fun removeObject()
    fun getUserIdWithExtension(): String
    fun uploadObject(file: MultipartFile): InputStream
    fun hasBanner(): Boolean
}