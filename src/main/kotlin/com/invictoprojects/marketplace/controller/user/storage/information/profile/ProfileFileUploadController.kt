package com.invictoprojects.marketplace.controller.user.storage.information.profile

import com.invictoprojects.marketplace.service.StorageService
import io.minio.UploadObjectArgs
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.multipart.MultipartFile
import java.io.FileInputStream

@RestController
@RequestMapping("/api/users/profile")
class ProfileFileUploadController(
    private val storageService: StorageService
) {

    @PostMapping("/upload/banner")
    fun uploadBanner(
        @RequestParam("file") file: MultipartFile) {
        storageService.uploadObject(file)
    }

}