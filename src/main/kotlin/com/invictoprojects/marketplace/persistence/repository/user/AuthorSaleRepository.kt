package com.invictoprojects.marketplace.persistence.repository.user

import com.invictoprojects.marketplace.persistence.model.user.extended.AuthorSale
import com.invictoprojects.marketplace.persistence.model.user.extended.Bid
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface AuthorSaleRepository : CrudRepository<AuthorSale, Long> {
}