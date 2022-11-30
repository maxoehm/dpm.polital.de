package com.invictoprojects.marketplace.persistence.model.user

import com.invictoprojects.marketplace.persistence.model.user.extended.*
import org.hibernate.annotations.CreationTimestamp
import org.springframework.lang.Nullable
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
    @CreationTimestamp
    var created_at: Date? = null,
    var updated_at: Date? = null,

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_information_id", nullable = false, insertable = false, updatable = false)
    var user_information_id: Long? = null,

    @Embedded
    var bids: Bid = Bid(),

    @Embedded
    var authorSale: AuthorSale = AuthorSale(),

    @Embedded
    var avatar: Avatar = Avatar(),

    @Embedded
    var banner: Banner = Banner(),



) {
    @OneToMany(mappedBy = "userInformation", cascade = [CascadeType.PERSIST, CascadeType.REMOVE], orphanRemoval = true)
    open var nfts: MutableList<Nft> = mutableListOf()


    @OneToMany(mappedBy = "userInformation", cascade = [CascadeType.PERSIST, CascadeType.REMOVE], orphanRemoval = true)
    open var hotCollections: MutableList<HotCollection> = mutableListOf()


    @OneToMany(mappedBy = "userInformation", cascade = [CascadeType.PERSIST, CascadeType.REMOVE], orphanRemoval = true)
    open var previewImages: MutableList<PreviewImage> = mutableListOf()
}
