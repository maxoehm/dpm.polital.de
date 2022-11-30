package com.invictoprojects.marketplace.dto.user

import com.invictoprojects.marketplace.persistence.model.user.UserInformation
import com.invictoprojects.marketplace.persistence.model.user.extended.PreviewImage
import org.hibernate.annotations.CreationTimestamp
import java.util.*
import javax.persistence.*

data class NftDto(
    var nftId: Int,
    var unique_id: String? = null,
    var category: String? = null,
    var status: String? = null,
    var item_type: String? = null,
    var collections: String? = null,
    var deadline: String? = null,
    var author_link: String? = null,
    var nft_link: String? = null,
    var bid_link: String? = null,
    var title: String? = null,
    var price: Double = 0.0,
    var bid: Int = 0,
    var max_bid: Int = 0,
    var likes: Int = 0,
    var description: String? = null,
    var views: Int = 0,
    var priceover: Double = 0.0,
    var author: Int = 0,
    var showcase: Boolean = false,
    var published_at: Date? = null,
    var updated_at: Date? = null,
    var userInformation: UserInformation? = null,
    var previewImage: PreviewImage? = null)
