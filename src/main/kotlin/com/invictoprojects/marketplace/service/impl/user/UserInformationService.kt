package com.invictoprojects.marketplace.service.impl.user

import com.invictoprojects.marketplace.dto.UserInformationDto
import com.invictoprojects.marketplace.dto.user.*
import com.invictoprojects.marketplace.persistence.model.Role
import com.invictoprojects.marketplace.persistence.model.User
import com.invictoprojects.marketplace.persistence.model.user.UserInformation
import com.invictoprojects.marketplace.persistence.model.user.extended.Nft
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity

interface UserInformationService {


    fun update(user: UserInformationDto): ResponseEntity<HttpStatus>

    fun findById(id: Long): UserInformation?

    fun getCurrentUser(): User

    fun commit(userInfo: UserInformationDto): UserInformation?

    fun getCurrentUserInformation(): UserInformation

    fun save(userInfo: UserInformationDto): UserInformation?

    fun addNft(nftDto: NftDto): Long

    fun addHotCollection(hotCollectionDto: HotCollectionDto)

    fun addAuthorSale(authorSaleDto: AuthorSaleDto)

    fun addBid(bidDto: BidDto)

    fun addPreviewImage(previewImageDto: PreviewImageDto)

    fun setBanner(extension: String)
}