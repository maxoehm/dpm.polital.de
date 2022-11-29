package com.invictoprojects.marketplace.persistence.model.user.extended

import com.invictoprojects.marketplace.persistence.model.user.UserInformation
import org.hibernate.annotations.CreationTimestamp
import java.util.*
import javax.persistence.*

@Entity
class HotCollection(
    var hotCollectionId: Int = 0,
    var unique_id: String? = null,
    @Column(name = "author", insertable = false, updatable = false)
    var author: Int = 0,
    @Column(name = "name", insertable = false, updatable = false)
    var name: String? = null,
    @Column(name = "published_at", insertable = false, updatable = false)
    var published_at: Date? = null,
    @CreationTimestamp
    var created_at: Date? = null,
    var updated_at: Date? = null,
) {


    @Embedded
    open var banner: Banner? = null

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    open var id: Long? = null

    @ManyToOne
    @JoinColumn(name = "user_information_user_information_id")
    open var userInformation: UserInformation? = null
}