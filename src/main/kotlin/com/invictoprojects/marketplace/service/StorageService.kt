package com.invictoprojects.marketplace.service

import io.minio.GetObjectResponse
import io.minio.UploadObjectArgs
import org.springframework.web.multipart.MultipartFile

interface StorageService {

    fun getBannerObject(bucketName: String, objectName: String): GetObjectResponse?
    fun removeObject(bucketName: String, objectName: String)

    fun getObjectName(): String

    fun uploadObject(file: MultipartFile)
}