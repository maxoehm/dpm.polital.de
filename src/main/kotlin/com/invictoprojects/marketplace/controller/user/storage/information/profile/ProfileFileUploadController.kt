package com.invictoprojects.marketplace.controller.user.storage.information.profile

import com.invictoprojects.marketplace.service.StorageService
import com.invictoprojects.marketplace.utils.FileTypeDefinitions
import com.invictoprojects.marketplace.utils.FileUtils
import io.minio.UploadObjectArgs
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.multipart.MultipartFile
import java.io.FileInputStream
import java.net.http.HttpHeaders
import java.net.http.HttpResponse

@RestController
@RequestMapping("/api/users/profile")
class ProfileFileUploadController(
    private val storageService: StorageService
) {

    @PostMapping("/upload/banner")
    fun uploadBanner(
        @RequestParam("file") file: MultipartFile): ResponseEntity<HttpStatus> {
        return if (FileUtils.isType(file, FileTypeDefinitions.Filetype.IMAGE)) {
            storageService.uploadObject(file)
            ResponseEntity(HttpStatus.OK)
        } else ResponseEntity(HttpStatus.UNSUPPORTED_MEDIA_TYPE)
    }

    @GetMapping("/get/banner", produces = [MediaType.IMAGE_PNG_VALUE])
    @ResponseBody
    fun getBanner(): ByteArray {
//        return ResponseEntity.ok().contentType(MediaType.parseMediaType(MediaType.IMAGE_PNG_VALUE)).header(HttpHeaders.Co).body(storageService.getUserBannerObject()?.readAllBytes()).
        return storageService.getUserBannerObject()?.readAllBytes()!!
    }


}