package com.invictoprojects.marketplace.persistence.model.user.extended

import com.invictoprojects.marketplace.persistence.model.user.UserInformation
import org.hibernate.Hibernate
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import java.util.*
import javax.persistence.*

@Entity
class Nft(
    var unique_id: String? = null,
    var category: String? = null,
    var status: String? = null,
    var item_type: String? = null,
    var collections: String? = null,
    @Column(name = "author_link", insertable = false, updatable = false)
    var title: String? = null,
    var price: Double = 0.0,

    var description: String? = null,
    var priceover: Double = 0.0,
    var showcase: Boolean = false, ) {

    @GeneratedValue(strategy = GenerationType.AUTO)
    var nftId: Long = 0
    var deadline: String? = null
    var author_link: String? = null
    var bid: Int = 0
    var max_bid: Int = 0
    var likes: Int = 0

    var views: Int = 0
    @Column(name = "published_at", insertable = false, updatable = false)
    var published_at: Date? = null
    @CreationTimestamp
    var created_at: Date? = null
    @UpdateTimestamp
    var updated_at: Date? = null
    var nft_link: String? = null
    var bid_link: String? = null
    @Column(name = "author", insertable = false, updatable = false)
    var author: Int = 0
    override fun hashCode(): Int = javaClass.hashCode()
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Nft

        if (nftId != other.nftId) return false
        if (unique_id != other.unique_id) return false
        if (category != other.category) return false
        if (status != other.status) return false
        if (item_type != other.item_type) return false
        if (collections != other.collections) return false
        if (price != other.price) return false
        if (bid != other.bid) return false
        if (author != other.author) return false
        if (showcase != other.showcase) return false
        if (published_at != other.published_at) return false
        if (created_at != other.created_at) return false
        if (updated_at != other.updated_at) return false
        if (id != other.id) return false

        return true
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    open var id: Long? = null

    @ManyToOne
    @JoinColumn(name = "user_information_user_information_id")
    open var userInformation: UserInformation? = null

    @OneToOne(cascade = [CascadeType.PERSIST, CascadeType.REMOVE], orphanRemoval = true)
    @JoinColumn(name = "id")
    open var previewImage: PreviewImage? = null
}