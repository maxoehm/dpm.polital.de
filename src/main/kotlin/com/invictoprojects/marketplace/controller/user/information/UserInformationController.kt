package com.invictoprojects.marketplace.controller.user.information

import com.invictoprojects.marketplace.dto.UserInformationDto
import com.invictoprojects.marketplace.dto.user.*
import com.invictoprojects.marketplace.persistence.model.user.UserInformationMin
import com.invictoprojects.marketplace.persistence.model.user.extended.Banner
import com.invictoprojects.marketplace.service.impl.storage.BannerStorageService
import com.invictoprojects.marketplace.service.impl.user.UserInformationService
import com.invictoprojects.marketplace.service.impl.user.UserService
import com.invictoprojects.marketplace.utils.FileTypeDefinitions
import com.invictoprojects.marketplace.utils.FileUtils
import org.springframework.context.annotation.Role
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile

@RestController
@RequestMapping("/api/users/e/")
@Role(0)
class UserInformationController(
    private val userInformationService: UserInformationService,
    private val bannerStorageService: BannerStorageService
    ) {

    /**
     * Intended for updating existing UserInformation data
     */
    @PostMapping("/update/info")
    fun updateUserInformation(@RequestBody userDto: UserInformationDto): ResponseEntity<UserInformationMin> {
        userInformationService.update(userDto)
        return ResponseEntity.ok().body(UserInformationMin.fromUserInformation(userInformationService.getCurrentUser().userInformation))
    }

    //ToDo: Bid needs to be mapped to an nft
    @PutMapping("/commit/bid")
    fun addBid(@RequestBody bidDto: BidDto): ResponseEntity<HttpStatus> {
        userInformationService.addBid(bidDto)
        return ResponseEntity.ok().body(HttpStatus.ACCEPTED)
    }

    @PutMapping("/commit/authorsale")
    fun addAuthorSale(@RequestBody authorSaleDto: AuthorSaleDto): ResponseEntity<HttpStatus> {
        userInformationService.addAuthorSale(authorSaleDto)
        return ResponseEntity.ok().body(HttpStatus.ACCEPTED)
    }

    @PutMapping("/commit/nft")
    fun addNft(@RequestBody nftDto: NftDto): ResponseEntity<HttpStatus> {
        userInformationService.addNft(nftDto)
        return ResponseEntity.ok().body(HttpStatus.ACCEPTED)
    }

    @PutMapping("/commit/hotcollection")
    fun addHotCollection(@RequestBody hotCollectionDto: HotCollectionDto): ResponseEntity<HttpStatus> {
        userInformationService.addHotCollection(hotCollectionDto)
        return ResponseEntity.ok().body(HttpStatus.ACCEPTED)
    }

    @PutMapping("/commit/previewimage")
    fun addPreviewImage(@RequestBody previewImageDto: PreviewImageDto): ResponseEntity<HttpStatus> {
        userInformationService.addPreviewImage(previewImageDto)
        return ResponseEntity.ok().body(HttpStatus.ACCEPTED)
    }

    @PostMapping("/commit/banner")
    fun uploadBanner(
        @RequestParam("file") file: MultipartFile
    ): ResponseEntity<HttpStatus> {
        return if (FileUtils.isType(file, FileTypeDefinitions.Filetype.IMAGE)) {
            bannerStorageService.uploadObject(file)
            ResponseEntity(HttpStatus.OK)
        } else ResponseEntity(HttpStatus.UNSUPPORTED_MEDIA_TYPE)
    }

}