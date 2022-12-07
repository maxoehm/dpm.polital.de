package com.invictoprojects.marketplace.service

import io.minio.GetObjectResponse
import io.minio.UploadObjectArgs
import org.springframework.web.multipart.MultipartFile

interface StorageService {

    fun getBannerObject(bucketName: String, objectName: String): GetObjectResponse?
    fun getUserBannerObject(): GetObjectResponse?
    fun removeObject()

    fun getUserId(): String

    fun uploadObject(file: MultipartFile)
    fun buildName(file: MultipartFile): String
}