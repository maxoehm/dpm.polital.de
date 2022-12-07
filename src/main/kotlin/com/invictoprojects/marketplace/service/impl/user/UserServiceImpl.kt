package com.invictoprojects.marketplace.service.impl.user

import com.invictoprojects.marketplace.dto.MappingUtils
import com.invictoprojects.marketplace.dto.UserInformationDto
import com.invictoprojects.marketplace.persistence.model.Role
import com.invictoprojects.marketplace.persistence.model.User
import com.invictoprojects.marketplace.persistence.model.user.UserInformation
import com.invictoprojects.marketplace.persistence.repository.UserInformationRepository
import com.invictoprojects.marketplace.persistence.repository.UserRepository
import com.invictoprojects.marketplace.service.impl.AuthenticationServiceImpl
import org.springframework.security.core.Authentication
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.Instant
import javax.persistence.EntityNotFoundException

@Service
class UserServiceImpl(private val userRepository: UserRepository) : UserService {

    override fun create(email: String, passwordHash: String): User {

        if (userRepository.existsByEmail(email)) {
            throw IllegalArgumentException("User with email $email already exists")
        }
        val user = User(email, passwordHash, Instant.now(), Role.USER, true)
        user.userInformation = genUserInformationEntity(user)
        return userRepository.save(user)
    }

    fun genUserInformationEntity(user: User): UserInformation {
        val userInformation = UserInformation()
        userInformation.email = user.email
        userInformation.user = user
        return userInformation
    }

    override fun delete(user: User) {
        if (user.id == null) {
            throw IllegalArgumentException("User id must not be null")
        } else if (!userRepository.existsById(user.id!!)) {
            throw EntityNotFoundException("User with id ${user.id} does not exist")
        }

        userRepository.delete(user)
    }

    override fun update(user: User): User {
        if (!userRepository.existsById(user.id!!)) {
            throw EntityNotFoundException("User with id ${user.id} does not exist")
        }
        val optionalCurrentUser = userRepository.findById(user.id!!)
        val current = optionalCurrentUser.get()
        user.apply {
            createdDate = current.createdDate
            role = current.role
            enabled = current.enabled
            passwordHash = current.passwordHash

            userInformation?.user = user
            userInformation = current.userInformation
            id = current.id
            userInformation!!.userInformationId = current.userInformation!!.userInformationId
        }
        return userRepository.save(user)
    }

    override fun updateInformation(user: User) {
        userRepository.save(user)
    }

    override fun findAll(): MutableIterable<User> {
        return userRepository.findAll()
    }

    //get logger
    val logger = org.slf4j.LoggerFactory.getLogger(AuthenticationServiceImpl::class.java)

    override fun findByEmail(email: String): User? {

        if (!userRepository.existsByEmail(email)) {
            throw EntityNotFoundException("User with email $email does not exist")
        } else {
            return userRepository.findByEmail(email)
        }
    }

    override fun findById(id: Long): User {
        if (!userRepository.existsById(id)) {
            throw EntityNotFoundException("User with id $id does not exist")
        } else {
            return userRepository.findById(id).get()
        }
    }

    @Transactional
    override fun disableById(id: Long): User {
        val user = findById(id)
        user.enabled = false
        return userRepository.save(user)
    }

    override fun findAllBySubscribedIsTrue() = userRepository.findAllBySubscribedIsTrue()

    override fun updatePasswordHash(user: User, newPasswordHash: String) {
        if (user.id == null) {
            throw IllegalArgumentException("User id must not be null")
        } else if (!userRepository.existsById(user.id!!)) {
            throw EntityNotFoundException("User with id ${user.id} does not exist")
        }

        user.passwordHash = newPasswordHash
        userRepository.save(user)
    }

    override fun updateRole(user: User, role: Role) {
        if (user.id == null) {
            throw IllegalArgumentException("User id must not be null")
        } else if (!userRepository.existsById(user.id!!)) {
            throw EntityNotFoundException("User with id ${user.id} does not exist")
        }

        user.role = role
        userRepository.save(user)
    }

    override fun getCurrentUser(): User {
        val authentication: Authentication = SecurityContextHolder.getContext().authentication
        val email = authentication.name

        return userRepository.findByEmail(email)
            ?: throw EntityNotFoundException("User with email $email does not exist")
    }
}
