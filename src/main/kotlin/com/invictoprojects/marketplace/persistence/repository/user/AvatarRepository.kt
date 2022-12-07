package com.invictoprojects.marketplace.persistence.repository.user

import com.invictoprojects.marketplace.persistence.model.user.extended.Avatar
import com.invictoprojects.marketplace.persistence.model.user.extended.Bid
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface AvatarRepository : CrudRepository<Avatar, Long> {
}