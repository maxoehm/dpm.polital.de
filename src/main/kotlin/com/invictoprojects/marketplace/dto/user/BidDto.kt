package com.invictoprojects.marketplace.dto.user

import java.util.*
import javax.persistence.Column

data class BidDto(
    var BidId: Int = 0,
    var value: Double = 0.0,
    var nft: Int = 0,
    var author: Int = 0,
    var published_at: Date? = null,
    var updated_at: Date? = null
)
