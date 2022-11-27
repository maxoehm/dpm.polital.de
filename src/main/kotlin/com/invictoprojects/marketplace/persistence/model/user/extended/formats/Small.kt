package com.invictoprojects.marketplace.persistence.model.user.extended.formats

import javax.persistence.*

@Entity
class Small(
    var name: String? = null,
    var hash: String? = null,
    var ext: String? = null,
    var mime: String? = null,
    var width: Int = 0,
    var height: Int = 0,
    var size: Double = 0.0,
    var path: Any? = null,
    var url: String? = null) {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    open var id: Long? = null
}