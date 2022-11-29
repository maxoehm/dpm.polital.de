package com.invictoprojects.marketplace.persistence.model.user.extended

import java.util.*
import javax.persistence.*

@Embeddable
class Bid(
    var BidId: Int = 0,
    var value: Double = 0.0,
    var nft: Int = 0,
    @Column(name = "author", insertable = false, updatable = false)
    var author: Int = 0,
    @Column(name = "published_at", insertable = false, updatable = false)
    var published_at: Date? = null,
    // var created_at: Date? = null,
    @Column(name = "updated_at", insertable = false, updatable = false)
    var updated_at: Date? = null) {


}