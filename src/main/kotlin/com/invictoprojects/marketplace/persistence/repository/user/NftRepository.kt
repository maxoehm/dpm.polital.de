package com.invictoprojects.marketplace.persistence.repository.user

import com.invictoprojects.marketplace.persistence.model.user.extended.Bid
import com.invictoprojects.marketplace.persistence.model.user.extended.Nft
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface NftRepository : CrudRepository<Nft, Long> {
}