package com.invictoprojects.marketplace.persistence.model.user.extended

import java.util.*
import javax.persistence.*

@Embeddable
class Avatar(
    var AvatarId: Int = 0,
    @Column(name = "name", insertable = false, updatable = false)
    var name: String? = null,
    @Column(name = "alternative_text", insertable = false, updatable = false)
    var alternativeText: String? = null,
    @Column(name = "caption", insertable = false, updatable = false)
    var caption: String? = null,
    @Column(name = "height", insertable = false, updatable = false)
    var width: Int = 0,
    @Column(name = "height", insertable = false, updatable = false)
    var height: Int = 0,
    @Column(name = "hash", insertable = false, updatable = false)
    var hash: String? = null,
    @Column(name = "ext", insertable = false, updatable = false)
    var ext: String? = null,
    @Column(name = "mime", insertable = false, updatable = false)
    var mime: String? = null,
    @Column(name = "size", insertable = false, updatable = false)
    var size: Double = 0.0,
    @Column(name = "url", insertable = false, updatable = false)
    var url: String? = null,
    @Column(name = "previewUrl", insertable = false, updatable = false)
    var previewUrl: String? = null,
    @Column(name = "provider", insertable = false, updatable = false)
    var provider: String? = null,
    @Column(name = "provider_metadaten", insertable = false, updatable = false)
    var provider_metadata: String? = null,
    // var created_at: Date? = null,
    @Column(name = "updated_at", insertable = false, updatable = false)
    var updated_at: Date? = null) {


    @Embedded
    open var formats: Formats? = null
}