package com.invictoprojects.marketplace.persistence.model.user.extended

import com.invictoprojects.marketplace.persistence.model.user.extended.formats.Large
import com.invictoprojects.marketplace.persistence.model.user.extended.formats.Medium
import com.invictoprojects.marketplace.persistence.model.user.extended.formats.Small
import com.invictoprojects.marketplace.persistence.model.user.extended.formats.Thumbnail
import javax.persistence.*

@Entity
class Formats() {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    open var id: Long? = null

    @OneToOne(orphanRemoval = true)
    @JoinColumn(name = "small_id")
    open var small: Small? = null

    @ManyToOne
    @JoinColumn(name = "medium_id")
    open var medium: Medium? = null

    @ManyToOne
    @JoinColumn(name = "large_id")
    open var large: Large? = null

    @ManyToOne
    @JoinColumn(name = "thumbnail_id")
    open var thumbnail: Thumbnail? = null
}