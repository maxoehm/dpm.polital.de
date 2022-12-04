package com.invictoprojects.marketplace.service.impl.user

import com.invictoprojects.marketplace.dto.MappingUtils
import com.invictoprojects.marketplace.dto.UserInformationDto
import com.invictoprojects.marketplace.dto.user.*
import com.invictoprojects.marketplace.persistence.model.User
import com.invictoprojects.marketplace.persistence.model.user.UserInformation
import com.invictoprojects.marketplace.persistence.model.user.extended.AuthorSale
import com.invictoprojects.marketplace.persistence.model.user.extended.Bid
import com.invictoprojects.marketplace.persistence.model.user.extended.Nft
import com.invictoprojects.marketplace.persistence.model.user.extended.PreviewImage
import com.invictoprojects.marketplace.persistence.repository.UserInformationRepository
import com.invictoprojects.marketplace.persistence.repository.UserRepository
import com.invictoprojects.marketplace.persistence.repository.user.*
import com.invictoprojects.marketplace.service.impl.url.LinkUtils
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Service
import javax.persistence.EntityNotFoundException

@Service
class UserInformationServiceImpl(
    private val userRepository: UserRepository,
    private val userInformationRepository: UserInformationRepository,
    private val bidRepository: BidRepository,
    private val authorSaleRepository: AuthorSaleRepository,
    private val nftRepository: NftRepository,
    private val avatarRepository: AvatarRepository,
    private val hotCollectionRepository: HotCollectionRepository,
    private val previewImageRepository: PreviewImageRepository,
    private val userService: UserService) : UserInformationService {

    fun update(user: UserInformation): UserInformation {
        if (!userRepository.existsById(user.userInformationId!!)) {
            throw EntityNotFoundException("User with id ${user.userInformationId} does not exist")
        }
        val optionalCurrentUser = userRepository.findById(user.userInformationId!!)
        return userInformationRepository.save(user)
    }

    /*
    /**
     * Method to update existing user information
     */
    override fun save(userInfo: UserInformationDto): UserInformation? {
        if (userInformationRepository.existsByEmail(getCurrentUser().email)) {
            val user = getCurrentUser()
            user.userInformation = MappingUtils.convertToEntity(userInfo)
            userService.updateInformation(user)
            return user.userInformation
        }
        throw EntityNotFoundException("User with does not exist")
    }

     */

    override fun update(userInfoDto: UserInformationDto): ResponseEntity<HttpStatus> {
        val current = getCurrentUser()
        val userInfo = MappingUtils.convertToEntity(userInfoDto)
        var usernameSuccess = true
        current.userInformation?.apply {
            email = current.email

            email = userInfo.email
            wallet = userInfo.wallet
            about = userInfo.about

            if (!userInfo.username?.let { userInformationRepository.existsByUsername(it) }!!) {
                username = userInfo.username
            } else {
                usernameSuccess = false
            }
        }

        userRepository.save(current)

        return if (usernameSuccess) {
            ResponseEntity.ok().body(HttpStatus.ACCEPTED)
        } else ResponseEntity(HttpStatus.CONFLICT)
    }

    override fun addNft(nftDto: NftDto) {
        val user = getCurrentUser()
        val nft: Nft = MappingUtils.convertToEntity(nftDto)
        nft.userInformation = user.userInformation

        nft.apply {
            author = user.id?.toInt()!!
            author_link = LinkUtils.getAuthorLink(getCurrentUserInformation())
            //ToDo: Implement bid
            //ToDo: Implement max bid
            //ToDo: Implement likes
            //ToDo: Implement views
            nft_link = LinkUtils.getNftLink(nft.nftId)
            bid_link = LinkUtils.getBidLink(nft.bid)

        }



        nftRepository.save(nft)
        user.userInformation?.nfts?.add(nft)
        userService.updateInformation(user)
    }

    override fun addHotCollection(hotCollectionDto: HotCollectionDto) {
        val user = getCurrentUser()
        val hotCollection = MappingUtils.convertToEntity(hotCollectionDto)
        hotCollection.userInformation = user.userInformation
        user.userInformation?.hotCollections?.add(hotCollection)
        hotCollectionRepository.save(hotCollection)
        userService.updateInformation(user)
    }

    override fun addAuthorSale(authorSaleDto: AuthorSaleDto) {
        val user = getCurrentUser()
        val authorSale: AuthorSale = MappingUtils.convertToEntity(authorSaleDto)
        authorSale.userInformation = user.userInformation
        user.userInformation?.authorSales?.add(authorSale)
        authorSaleRepository.save(authorSale)
        userService.updateInformation(user)
    }

    override fun addBid(bidDto: BidDto) {
        val user = getCurrentUser()
        val bid: Bid = MappingUtils.convertToEntity(bidDto)
        bid.userInformation = user.userInformation
        user.userInformation?.bids?.add(bid)
        bidRepository.save(bid)
        userService.updateInformation(user)
    }

    override fun addPreviewImage(previewImageDto: PreviewImageDto) {
        val user = getCurrentUser()
        val previewImage: PreviewImage = MappingUtils.convertToEntity(previewImageDto)
        previewImage.userInformation = user.userInformation
        user.userInformation?.previewImages?.add(previewImage)
        previewImageRepository.save(previewImage)
        userService.updateInformation(user)
    }

    /**
     * Method for commiting new user information
     */
    override fun commit(userInfo: UserInformationDto): UserInformation? {
        val userMain = getCurrentUser()
        val user = MappingUtils.convertToEntity(userInfo)

        if (!userInformationRepository.existsByEmail(getCurrentUser().email)) {

            userMain.userInformation = user
            user.user = userMain
            userRepository.save(userMain)
        }
        return userMain.userInformation
    }

    override fun findById(id: Long): UserInformation {
        if (!userInformationRepository.existsById(id)) {
            throw EntityNotFoundException("User with id $id does not exist")
        } else {
            return userInformationRepository.findById(id).get()
        }
    }

    override fun getCurrentUser(): User {
        val email = SecurityContextHolder.getContext().authentication.name
        return userRepository.findByEmail(email)
            ?: throw EntityNotFoundException("User with email $email does not exist")
    }

    override fun getCurrentUserInformation(): UserInformation {
        val email = SecurityContextHolder.getContext().authentication.name
        return userInformationRepository.findByUsername(email).get()
    }

    override fun save(userInfo: UserInformationDto): UserInformation? {
        TODO("Not yet implemented")
    }

}
