package com.invictoprojects.marketplace.controller.user.information

import com.invictoprojects.marketplace.dto.UserInformationDto
import com.invictoprojects.marketplace.dto.user.*
import com.invictoprojects.marketplace.service.impl.user.UserInformationService
import com.invictoprojects.marketplace.service.impl.user.UserService
import org.springframework.context.annotation.Role
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/users/e/")
@Role(0)
class UserInformationController(
    private val userInformationService: UserInformationService
    ) {

    /**
     * Intended for updating existing UserInformation data
     */
    @PutMapping("/update/info")
    fun updateUserInformation(@RequestBody userDto: UserInformationDto): ResponseEntity<HttpStatus> {
        userInformationService.save(userDto)
        return ResponseEntity.ok().body(HttpStatus.ACCEPTED)
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

}