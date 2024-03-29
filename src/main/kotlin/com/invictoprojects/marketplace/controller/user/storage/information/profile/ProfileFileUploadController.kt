package com.invictoprojects.marketplace.controller.user.storage.information.profile

import com.invictoprojects.marketplace.service.StorageService
import com.invictoprojects.marketplace.utils.FileTypeDefinitions
import com.invictoprojects.marketplace.utils.FileUtils
import org.apache.commons.io.IOUtils
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile

@RestController
@RequestMapping("/api/users/profile")
class ProfileFileUploadController(
    @Qualifier("profileStorageService") private val storageService: StorageService
) {

    @PostMapping("/upload/profile", produces = [MediaType.IMAGE_PNG_VALUE])
    fun uploadprofile(
        @RequestParam("file") file: MultipartFile): ResponseEntity<ByteArray> {
        return if (FileUtils.isType(file, FileTypeDefinitions.Filetype.IMAGE)) {
            val e = storageService.uploadObject(file)
            return ResponseEntity(IOUtils.toByteArray(e), HttpStatus.OK)
        } else ResponseEntity(HttpStatus.UNSUPPORTED_MEDIA_TYPE)
    }

    /**
     * Endpoint returns image of user profile as byte array
     */
    @GetMapping("/get/profile", produces = [MediaType.IMAGE_PNG_VALUE])
    @ResponseBody
    fun getprofile(): ResponseEntity<ByteArray> {
        return ResponseEntity(IOUtils.toByteArray(storageService.getObject().get()), HttpStatus.OK)
    }

    @GetMapping("/get/profile/url")
    fun getprofileUrl(): String {
        return try {
            storageService.getUrl()
        } catch (e: Exception) {
            "https://www.shutterstock.com/image-vector/no-signal-poster-colorful-error-260nw-1042680049.jpg"
        }
    }

}