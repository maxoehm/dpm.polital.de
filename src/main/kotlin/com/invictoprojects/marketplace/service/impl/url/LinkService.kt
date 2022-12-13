package com.invictoprojects.marketplace.service.impl.url

import com.invictoprojects.marketplace.persistence.model.user.UserInformation
import org.springframework.stereotype.Service

object LinkUtils {

    //ToDo: Implement functions of this class

    fun getAuthorLink(user: UserInformation): String {
        return "https://invicto.marketplace.com/user/${user.username}/profile"
    }

    fun getNftLink(nftId: Long): String {
        return "https://invicto.marketplace.com/nft/$nftId"
    }

    fun getBidLink(nftId: Int): String {
        return "https://invicto.marketplace.com/nft/$nftId/bid"
    }

}