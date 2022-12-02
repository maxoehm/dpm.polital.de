package com.invictoprojects.marketplace.persistence.repository.user

import com.invictoprojects.marketplace.persistence.model.user.extended.Bid
import com.invictoprojects.marketplace.persistence.model.user.extended.HotCollection
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface HotCollectionRepository : CrudRepository<HotCollection, Long> {
}