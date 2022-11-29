package com.invictoprojects.marketplace.persistence.model.user.extended.formats

import javax.persistence.*

@Embeddable
class Small(
    @Column(name = "name", insertable = false, updatable = false)
var name: String? = null,
    @Column(name = "hash", insertable = false, updatable = false)
var hash: String? = null,
    @Column(name = "ext", insertable = false, updatable = false)
    var ext: String? = null,
    @Column(name = "mime", insertable = false, updatable = false)
var mime: String? = null,
    @Column(name = "height", insertable = false, updatable = false)
var width: Int = 0,
    @Column(name = "height", insertable = false, updatable = false)
    var height: Int = 0,
    @Column(name = "size", insertable = false, updatable = false)
var size: Double = 0.0,
    @Column(name = "path", insertable = false, updatable = false)
var path: String? = null,
    @Column(name = "url", insertable = false, updatable = false)
    var url: String? = null) {

}