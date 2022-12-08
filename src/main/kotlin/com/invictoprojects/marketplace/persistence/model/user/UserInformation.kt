package com.invictoprojects.marketplace.persistence.model.user

import com.invictoprojects.marketplace.persistence.model.User
import com.invictoprojects.marketplace.persistence.model.user.extended.*
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import java.util.*
import javax.persistence.*

// version 2.11.1
@Entity
@Table(name = "user_information")
class UserInformation (

    var username: String? = null,
    var email: String? = null,
    var social: String? = null,
    var wallet: String? = null,
    var followers: Int = 0,
    var about: String? = null,
    var published_at: Date? = null,

    @OneToMany(mappedBy = "userInformation", cascade = [CascadeType.PERSIST, CascadeType.REMOVE], orphanRemoval = true)
    open var nfts: MutableList<Nft> = mutableListOf(),


    @OneToMany(mappedBy = "userInformation", cascade = [CascadeType.PERSIST, CascadeType.REMOVE], orphanRemoval = true)
    open var hotCollections: MutableList<HotCollection> = mutableListOf(),

    @OneToMany(mappedBy = "userInformation", cascade = [CascadeType.PERSIST, CascadeType.REMOVE], orphanRemoval = true)
    open var previewImages: MutableList<PreviewImage> = mutableListOf(),

    @OneToMany(mappedBy = "userInformation", cascade = [CascadeType.PERSIST, CascadeType.REMOVE], orphanRemoval = true)
    open var authorSales: MutableList<AuthorSale> = mutableListOf(),

    @OneToMany(mappedBy = "userInformation", cascade = [CascadeType.PERSIST, CascadeType.REMOVE], orphanRemoval = true)
    open var bids: MutableList<Bid> = mutableListOf(),

    /*
    @OneToOne(cascade = [CascadeType.PERSIST, CascadeType.REMOVE], orphanRemoval = true)
    @JoinColumn(name = "banner_user_info_id")
    var banner: Banner? = null,
     */



    @OneToOne(cascade = [CascadeType.PERSIST, CascadeType.REMOVE], orphanRemoval = true)
    @JoinColumn(name = "avatar_id")
    open var avatar: Avatar? = null
) {

    var banner: String = ""

    @CreationTimestamp
    var created_at: Date? = null
    @UpdateTimestamp
    var updated_at: Date? = null

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_information_id", nullable = false, insertable = false, updatable = false)
    var userInformationId: Long? = null

    @OneToOne(cascade = [CascadeType.ALL], orphanRemoval = true)
    @JoinColumn(name = "id")
    var user: User? = null



}
