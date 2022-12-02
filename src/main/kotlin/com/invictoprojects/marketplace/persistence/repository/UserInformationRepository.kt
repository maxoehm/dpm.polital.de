package com.invictoprojects.marketplace.persistence.repository

import com.invictoprojects.marketplace.persistence.model.user.UserInformation
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface UserInformationRepository : CrudRepository<UserInformation, Long> {

    @Query("select u from UserInformation u where u.userInformationId = ?1")
    override fun findById(user_information_id: Long): Optional<UserInformation>
    fun findByUsername(username: String?): Optional<UserInformation>
    fun findByEmail(email: String): Optional<UserInformation>
    fun existsByEmail(email: String): Boolean


}