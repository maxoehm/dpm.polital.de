package com.invictoprojects.marketplace.persistence.model.user.extended

import com.invictoprojects.marketplace.persistence.model.user.UserInformation
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import org.springframework.lang.Nullable
import java.util.*
import javax.persistence.*

@Entity
class Bid(
    var BidId: Int = 0,
    var value: Double = 0.0,
    var nft: Int = 0,
    @Column(name = "author", insertable = false, updatable = false)
    var author: Int = 0,
    @Column(name = "published_at", insertable = false, updatable = false)
    var published_at: Date? = null,


) {

    @CreationTimestamp
    var created_at: Date? = null
    @UpdateTimestamp
    var updated_at: Date? = null

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    open var id: Long? = null

    @ManyToOne(cascade = [CascadeType.PERSIST, CascadeType.REMOVE])
    @JoinColumn(name = "user_information_user_information_id")
    var userInformation: UserInformation? = null

}