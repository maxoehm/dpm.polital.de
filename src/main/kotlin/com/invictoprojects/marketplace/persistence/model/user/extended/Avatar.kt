package com.invictoprojects.marketplace.persistence.model.user.extended

import java.util.*
import javax.persistence.*

@Entity
class Avatar(        var AvatarId: Int = 0,
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
                     var previewUrl: Any? = null,
                     var provider: String? = null,
                     var provider_metadata: Any? = null,
                     var created_at: Date? = null,
                     var updated_at: Date? = null) {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    open var id: Long? = null


    @OneToOne(orphanRemoval = true)
    @JoinColumn(name = "formats_id")
    open var formats: Formats? = null
}