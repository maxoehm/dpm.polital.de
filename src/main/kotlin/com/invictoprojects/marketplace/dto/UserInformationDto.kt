package com.invictoprojects.marketplace.dto

import com.fasterxml.jackson.annotation.JsonFormat
import com.invictoprojects.marketplace.persistence.model.user.extended.AuthorSale
import com.invictoprojects.marketplace.persistence.model.user.extended.Avatar
import com.invictoprojects.marketplace.persistence.model.user.extended.Banner
import com.invictoprojects.marketplace.persistence.model.user.extended.Bid
import org.hibernate.annotations.CreationTimestamp
import java.util.*
import javax.persistence.*
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull

data class UserInformationDto(

    @NotBlank
    var username: String? = null,
    var social: String? = null,
    var wallet: String? = null,
    var followers: Int = 0,
    var about: String? = null,
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    var published_at: Date? = null,

    @NotBlank
    @NotNull
    var user_information_id: Long? = null,

    var bids: Bid = Bid(),
    var authorSale: AuthorSale = AuthorSale(),
    var avatar: Avatar = Avatar(),
    var banner: Banner = Banner(),
) {

}