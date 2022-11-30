package com.invictoprojects.marketplace.dto.user

import com.invictoprojects.marketplace.persistence.model.user.extended.formats.Large
import com.invictoprojects.marketplace.persistence.model.user.extended.formats.Medium
import com.invictoprojects.marketplace.persistence.model.user.extended.formats.Small
import com.invictoprojects.marketplace.persistence.model.user.extended.formats.Thumbnail
import javax.persistence.Embedded

data class FormatsDto(
    var small: Small? = null,
    var medium: Medium? = null,
    var large: Large? = null,
    var thumbnail: Thumbnail? = null
)
