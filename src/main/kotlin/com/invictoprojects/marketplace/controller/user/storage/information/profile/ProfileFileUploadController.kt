package com.invictoprojects.marketplace.controller.user.storage.information.profile

import com.invictoprojects.marketplace.service.StorageService
import com.invictoprojects.marketplace.utils.FileTypeDefinitions
import com.invictoprojects.marketplace.utils.FileUtils
import org.apache.commons.io.IOUtils
import org.slf4j.LoggerFactory
import org.springframework.core.io.Resource
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile

@RestController
@RequestMapping("/api/users/profile")
class ProfileFileUploadController(
    private val storageService: StorageService
) {

    @PostMapping("/upload/banner", produces = [MediaType.IMAGE_PNG_VALUE])
    fun uploadBanner(
        @RequestParam("file") file: MultipartFile): ResponseEntity<ByteArray> {
        return if (FileUtils.isType(file, FileTypeDefinitions.Filetype.IMAGE)) {
            val e = storageService.uploadObject(file)
            return ResponseEntity(IOUtils.toByteArray(e), HttpStatus.OK)
        } else ResponseEntity(HttpStatus.UNSUPPORTED_MEDIA_TYPE)
    }

    @GetMapping("/get/banner", produces = [MediaType.IMAGE_PNG_VALUE])
    @ResponseBody
    fun getBanner(): ResponseEntity<ByteArray> {
        return ResponseEntity(IOUtils.toByteArray(storageService.getUserBannerObject().get()), HttpStatus.OK)
    }


}