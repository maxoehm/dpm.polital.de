package com.invictoprojects.marketplace.dto.user

import com.invictoprojects.marketplace.persistence.model.user.UserInformation
import com.invictoprojects.marketplace.persistence.model.user.extended.Formats
import java.util.*
import javax.persistence.*

data class PreviewImageDto(
    var imageId: Int = 0,
    var name: String? = null,
    var alternativeText: String? = null,
    var caption: String? = null,
    var width: Int = 0,
    var height: Int = 0,
    var hash: String? = null,
    var ext: String? = null,
    var mime: String? = null,
    var size: Double = 0.0,
    var url: String? = null,
    var previewUrl: String? = null,
    var provider: String? = null,
    var provider_metadata: String? = null,
    var formats: Formats? = null,
    var userInformation: UserInformation? = null

)
