package com.invictoprojects.marketplace.persistence.model.user.extended

import com.invictoprojects.marketplace.persistence.model.user.UserInformation
import java.util.*
import javax.persistence.*

@Entity
class PreviewImage(

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    var id: Long? = null,

    var imageId: Int = 0,
    @Column(name = "name", insertable = false, updatable = false)
    var name: String? = null,
    @Column(name = "alternative_text", insertable = false, updatable = false)
    var alternativeText: String? = null,
    @Column(name = "caption", insertable = false, updatable = false)
    var caption: String? = null,
    @Column(name = "height", insertable = false, updatable = false)
    var width: Int = 0,
    var height: Int = 0,
    @Column(name = "hash", insertable = false, updatable = false)
    var hash: String? = null,
    var ext: String? = null,
    @Column(name = "mime", insertable = false, updatable = false)
    var mime: String? = null,
    @Column(name = "size", insertable = false, updatable = false)
    var size: Double = 0.0,
    var url: String? = null,
    var previewUrl: String? = null,
    @Column(name = "provider", insertable = false, updatable = false)
    var provider: String? = null,
    @Column(name = "provider_metadaten", insertable = false, updatable = false)
    var provider_metadata: String? = null,
    // var created_at: Date? = null,
    var updated_at: Date? = null,

    @OneToOne(cascade = [CascadeType.ALL], orphanRemoval = true)
    @JoinColumn(name = "id")
    open var formats: Formats? = null) {

    @ManyToOne
    @JoinColumn(name = "user_information_user_information_id")
    open var userInformation: UserInformation? = null
}