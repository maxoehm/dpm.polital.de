package com.invictoprojects.marketplace.dto.user

import com.invictoprojects.marketplace.persistence.model.user.UserInformation
import com.invictoprojects.marketplace.persistence.model.user.extended.*
import java.math.BigInteger
import java.util.*
import javax.validation.constraints.Email
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull

class UserInformationDto (

    @NotBlank
    var username: String,

    @NotNull
    @Email
    var email: String,

    @NotNull
    var subscribed: Boolean,
    @NotNull
    var social: String,
    var wallet: String,
    var bid: Bid,
    var author_sale: AuthorSale,
    var about: String,
    var published_at: Date,
    var updated_at: Date,
    var avatar: Avatar,
    var banner: Banner,
    var nfts: ArrayList<Nft>,
    var hot_collections: ArrayList<HotCollection>,
    var followers: Int = 0

)