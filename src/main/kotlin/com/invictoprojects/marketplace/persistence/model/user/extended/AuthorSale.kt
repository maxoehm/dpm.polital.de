package com.invictoprojects.marketplace.persistence.model.user.extended

import com.invictoprojects.marketplace.persistence.model.user.UserInformation
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import org.springframework.lang.Nullable
import java.util.*
import javax.persistence.*

@Entity
class AuthorSale(
    var sales: Double = 0.0,
    var volume: Double = 0.0,
    var daily_sales: Double = 0.0,
    var weekly_sales: Double = 0.0,
    var floor_price: Double = 0.0,
    var owners: Double = 0.0,
    var assets: Double = 0.0,
    var authorId: Long = 0,
    var published_at: Date? = null,
    @CreationTimestamp
    var created_at: Date? = null,
    @UpdateTimestamp
    var updated_at: Date? = null,

    @ManyToOne(cascade = [CascadeType.PERSIST, CascadeType.REMOVE])
    @JoinColumn(name = "user_information_user_information_id")
    var userInformation: UserInformation? = null) {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "author_sale_id", nullable = false)
    open var id: Long? = null


}