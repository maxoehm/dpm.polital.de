package com.invictoprojects.marketplace.persistence.model.user.extended

import java.util.*
import javax.persistence.*

@Entity
class Bid(
    var BidId: Int = 0,
    var value: Double = 0.0,
    var nft: Int = 0,
    var author: Int = 0,
    var published_at: Date? = null,
    var created_at: Date? = null,
    var updated_at: Date? = null) {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    open var id: Long? = null


}