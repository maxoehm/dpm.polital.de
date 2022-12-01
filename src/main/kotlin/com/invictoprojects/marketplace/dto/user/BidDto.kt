package com.invictoprojects.marketplace.dto.user

import com.invictoprojects.marketplace.persistence.model.user.UserInformation
import java.util.*
import javax.persistence.Column

data class BidDto(
    var BidId: Int = 0,
    var value: Double = 0.0,
    var nft: Int = 0,
    var author: Int = 0,
    var published_at: Date? = null,
    var userInformation: UserInformation? = null,
    var id: Long? = null
)
