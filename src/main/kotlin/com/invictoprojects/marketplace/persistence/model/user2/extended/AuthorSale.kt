package com.invictoprojects.marketplace.persistence.model.user.extended

import java.util.*
import javax.persistence.*

@Embeddable
class AuthorSale(
    @Column(name = "authorSaleId", insertable = false, updatable = false)
    var authorSaleId: Int = 0,
    var sales: Double = 0.0,
    var volume: Double = 0.0,
    var daily_sales: Double = 0.0,
    var weekly_sales: Double = 0.0,
    var floor_price: Double = 0.0,
    var owners: Double = 0.0,
    var assets: Double = 0.0,
    @Column(name = "author", insertable = false, updatable = false)
    var author: Int = 0,
    @Column(name = "published_at", insertable = false, updatable = false)
    var published_at: Date? = null,
    // var created_at: Date? = null,
        @Column(name = "updated_at", insertable = false, updatable = false)
    var updated_at: Date? = null) {


}