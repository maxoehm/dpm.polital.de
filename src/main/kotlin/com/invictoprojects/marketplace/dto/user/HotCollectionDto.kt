package com.invictoprojects.marketplace.dto.user

import com.invictoprojects.marketplace.persistence.model.user.UserInformation
import com.invictoprojects.marketplace.persistence.model.user.extended.Banner
import org.hibernate.annotations.CreationTimestamp
import java.util.*
import javax.persistence.*

data class HotCollectionDto(
    var hotCollectionId: Int = 0,
    var unique_id: String? = null,
    var author: Int = 0,
    var name: String? = null,
    var published_at: Date? = null,
    var updated_at: Date? = null,
    var banner: Banner? = null,
    var userInformation: UserInformation? = null,

)
