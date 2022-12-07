package com.invictoprojects.marketplace.persistence.model.user

import com.invictoprojects.marketplace.persistence.model.user.extended.*
import java.util.*
import javax.persistence.*

// version 2.11.1
@Entity
@Table(name = "user_information")
class UserInformation (
    var username: String? = null,
    var social: String? = null,
    var wallet: String? = null,
    var followers: Int = 0,
    var about: String? = null,
    var published_at: Date? = null,
    var created_at: Date? = null,
    var updated_at:Date? = null,

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_information_id", nullable = false)
     var id: Long? = null,

    @ManyToOne
    @JoinColumn(name = "bids_id")
    var bids: Bid? = null,

    @ManyToOne
    @JoinColumn(name = "author_sale_id")
    var authorSale: AuthorSale? = null,

    @OneToOne(orphanRemoval = true)
    @JoinColumn(name = "avatar_id")
    var avatar: Avatar? = null,

    @OneToOne(orphanRemoval = true)
    @JoinColumn(name = "banner_id")
    var banner: Banner? = null,

    @ElementCollection
    @CollectionTable(name = "hot_collections", joinColumns = [JoinColumn(name = "id")])
    var hotCollections: List<HotCollection>? = null,

    @ElementCollection
    @CollectionTable(name = "nfts", joinColumns = [JoinColumn(name = "id")])
    var nft: List<Nft>? = null


) { }
