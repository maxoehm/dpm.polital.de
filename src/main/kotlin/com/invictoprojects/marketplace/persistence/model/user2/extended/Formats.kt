package com.invictoprojects.marketplace.persistence.model.user.extended

import com.invictoprojects.marketplace.persistence.model.user.extended.formats.Large
import com.invictoprojects.marketplace.persistence.model.user.extended.formats.Medium
import com.invictoprojects.marketplace.persistence.model.user.extended.formats.Small
import com.invictoprojects.marketplace.persistence.model.user.extended.formats.Thumbnail
import javax.persistence.*

@Embeddable
class Formats() {

    @Embedded
    var small: Small? = null

    @Embedded
    var medium: Medium? = null

    @Embedded
    var large: Large? = null

    @Embedded
    var thumbnail: Thumbnail? = null
}