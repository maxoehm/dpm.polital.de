package com.invictoprojects.marketplace.persistence.model.user.extended

import com.invictoprojects.marketplace.persistence.model.user.UserInformation
import java.util.*
import javax.persistence.*

@Embeddable
class HotCollection(        var hotCollectionId: Int = 0,
                            var unique_id: String? = null,
                            var author: Int = 0,
                            var name: String? = null,
                            var published_at: Date? = null,
                            var created_at: Date? = null,
                            var updated_at: Date? = null,
                            ) {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    open var id: Long? = null

    @OneToOne
    @JoinColumn(name = "banner_ID")
    open var banner: Banner? = null
}