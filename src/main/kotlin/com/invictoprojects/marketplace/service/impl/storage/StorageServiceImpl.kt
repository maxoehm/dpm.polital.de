package com.invictoprojects.marketplace.service.impl.storage

import com.invictoprojects.marketplace.config.MinIOConfig
import com.invictoprojects.marketplace.service.StorageService
import com.invictoprojects.marketplace.service.impl.user.UserService
import io.minio.*
import org.springframework.stereotype.Service
import java.io.InputStream


@Service
class StorageServiceImpl(
    private val bannerStorageService: BannerStorageService
) {

    fun getBannerStorage(): BannerStorageService {
        return bannerStorageService
    }
    
}