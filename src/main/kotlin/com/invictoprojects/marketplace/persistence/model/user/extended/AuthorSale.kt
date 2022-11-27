package com.invictoprojects.marketplace.persistence.model.user.extended

import java.util.*
import javax.persistence.*

@Entity
class AuthorSale(
    var AuthorSaleId: Int = 0,
    var sales: Double = 0.0,
    var volume: Double = 0.0,
    var daily_sales: Double = 0.0,
    var weekly_sales: Double = 0.0,
    var floor_price: Double = 0.0,
    var owners: Double = 0.0,
    var assets: Double = 0.0,
    var author: Int = 0,
    var published_at: Date? = null,
    var created_at: Date? = null,
    var updated_at: Date? = null) {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    open var id: Long? = null

}