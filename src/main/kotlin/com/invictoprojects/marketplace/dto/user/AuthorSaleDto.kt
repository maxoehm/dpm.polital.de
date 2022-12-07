package com.invictoprojects.marketplace.dto.user

import com.invictoprojects.marketplace.persistence.model.user.UserInformation
import org.springframework.lang.Nullable
import java.util.*
import javax.persistence.Column

data class AuthorSaleDto(
    var authorSaleId: Int = 0,
    var sales: Double = 0.0,
    var volume: Double = 0.0,
    var daily_sales: Double = 0.0,
    var weekly_sales: Double = 0.0,
    var floor_price: Double = 0.0,
    var owners: Double = 0.0,
    var assets: Double = 0.0,
    var author: Int = 0,
    var published_at: Date? = null,
    var authorId: Long = 0,
    var userInformation: UserInformation
    )


