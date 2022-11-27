package com.invictoprojects.marketplace.persistence.model.user.extended

import org.hibernate.Hibernate
import java.util.*
import javax.persistence.*

@Embeddable
class Nft(
    var nftId: Int = 0,
    var unique_id: Any? = null,
    var category: String? = null,
    var status: String? = null,
    var item_type: String? = null,
    var collections: String? = null,
    var deadline: String? = null,
    var author_link: String? = null,
    var nft_link: String? = null,
    var bid_link: String? = null,
    var title: String? = null,
    var price: Double = 0.0,
    var bid: Int = 0,
    var max_bid: Int = 0,
    var likes: Int = 0,
    var description: String? = null,
    var views: Int = 0,
    var priceover: Double = 0.0,
    var author: Int = 0,
    var showcase: Boolean = false,
    var published_at: Date? = null,
    var created_at: Date? = null,
    var updated_at: Date? = null,
    ) {

    @OneToOne(orphanRemoval = true)
    @JoinColumn(name = "preview_image_id")
    open var previewImage: PreviewImage? = null

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    open var id: Long? = null

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || Hibernate.getClass(this) != Hibernate.getClass(other)) return false
        other as Nft

        return id != null && id == other.id
    }

    override fun hashCode(): Int = javaClass.hashCode()
}