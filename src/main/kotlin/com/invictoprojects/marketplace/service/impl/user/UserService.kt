package com.invictoprojects.marketplace.service.impl.user

import com.invictoprojects.marketplace.dto.UserInformationDto
import com.invictoprojects.marketplace.persistence.model.Role
import com.invictoprojects.marketplace.persistence.model.User
import com.invictoprojects.marketplace.persistence.model.user.UserInformation

interface UserService {
    fun create(email: String, passwordHash: String): User

    fun delete(user: User)

    fun update(user: User): User

    fun findAll(): MutableIterable<User>

    fun findByEmail(email: String): User?

    fun findById(id: Long): User?

    fun disableById(id: Long): User

    fun updatePasswordHash(user: User, newPasswordHash: String)

    fun updateRole(user: User, role: Role)

    fun getCurrentUser(): User

    fun findAllBySubscribedIsTrue(): MutableIterable<User>

    fun updateInformation(user: User)
}
