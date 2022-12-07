package com.invictoprojects.marketplace.utils

import org.springframework.web.multipart.MultipartFile

object FileUtils : FileTypeDefinitions {

    fun classify(file: MultipartFile): FileTypeDefinitions.Filetype {
        return when {
            imageTypes.contains(file.contentType) -> FileTypeDefinitions.Filetype.IMAGE
            videoTypes.contains(file.contentType) -> FileTypeDefinitions.Filetype.VIDEO
            audioTypes.contains(file.contentType) -> FileTypeDefinitions.Filetype.AUDIO
            documentTypes.contains(file.contentType) -> FileTypeDefinitions.Filetype.DOCUMENT
            archiveTypes.contains(file.contentType) -> FileTypeDefinitions.Filetype.ARCHIVE
            else -> FileTypeDefinitions.Filetype.OTHER
        }
    }

    fun isImage(file: MultipartFile): Boolean {
        return imageTypes.contains(file.contentType)
    }

    fun isType(file: MultipartFile, type: FileTypeDefinitions.Filetype): Boolean {
        return classify(file) == type
    }

}