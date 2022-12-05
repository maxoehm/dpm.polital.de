package com.invictoprojects.marketplace.utils

import org.springframework.web.multipart.MultipartFile

sealed interface FileTypeDefinitions {
    enum class Filetype {
        IMAGE, VIDEO, AUDIO, DOCUMENT, ARCHIVE, OTHER
    }

    val imageTypes: List<String> get() = listOf("image/png", "image/jpeg", "image/jpg", "image/webp")

    val videoTypes: List<String> get() = listOf("video/mp4", "video/webm", "video/ogg")

    val audioTypes: List<String> get() = listOf("audio/mpeg", "audio/ogg", "audio/wav")

    val documentTypes: List<String> get() = listOf("application/pdf", "application/msword", "text/plain")

    val archiveTypes: List<String> get() = listOf("application/zip")



}