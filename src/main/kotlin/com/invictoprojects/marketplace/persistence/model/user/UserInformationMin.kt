package com.invictoprojects.marketplace.persistence.model.user

import com.invictoprojects.marketplace.persistence.model.User
import com.invictoprojects.marketplace.persistence.model.user.extended.Avatar
import com.invictoprojects.marketplace.persistence.model.user.extended.Banner
import java.sql.Date

class UserInformationMin(
    var userInformationId: Long? = null,
    var username: String? = null,
    var email: String? = null,
    var social: String? = null,
    var wallet: String? = null,
    var followers: Int = 0,
    var about: String? = null,
    var published_at: Date? = null,

    var banner: Boolean = false,
    var avatar: Avatar? = null,
) {

    companion object {
        fun fromUserInformation(userInformation: UserInformation?): UserInformationMin {
            if (userInformation == null) {
                return UserInformationMin()
            }
            return UserInformationMin(
                userInformationId = userInformation.userInformationId,
                username = userInformation.username,
                email = userInformation.email,
                social = userInformation.social,
                wallet = userInformation.wallet,
                followers = userInformation.followers,
                about = userInformation.about,
                avatar = userInformation.avatar
            )
        }
    }

}