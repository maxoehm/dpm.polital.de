package com.invictoprojects.marketplace.service.impl

import com.invictoprojects.marketplace.dto.MappingUtils
import com.invictoprojects.marketplace.dto.UserInformationDto
import com.invictoprojects.marketplace.persistence.model.Role
import com.invictoprojects.marketplace.persistence.model.User
import com.invictoprojects.marketplace.persistence.model.user.UserInformation
import com.invictoprojects.marketplace.persistence.repository.UserInformationRepository
import com.invictoprojects.marketplace.persistence.repository.UserRepository
import com.invictoprojects.marketplace.service.UserService
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.Instant
import javax.persistence.EntityNotFoundException

@Service
class UserServiceImpl(private val userRepository: UserRepository, private val userInformationRepository: UserInformationRepository) : UserService {

    override fun create(username: String, email: String, passwordHash: String): User {
        if (userRepository.existsByEmail(email)) {
            throw IllegalArgumentException("User with email $email already exists")
        }
        val user = User(username, email, passwordHash, Instant.now(), Role.USER, true)
        return userRepository.save(user)
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
        }
        return userRepository.save(user)
    }

    override fun updateByDto(user: UserInformationDto): UserInformation {

        val currentUser: UserInformation = MappingUtils.convertToEntity(user);

        if (!userInformationRepository.existsById(user.user_information_id!!)) {
            saveUserInformation(currentUser)
            throw EntityNotFoundException("User with id ${currentUser.user_information_id} does not exist")
        }

        val userEntity = userInformationRepository.findById(currentUser.user_information_id!!).get()

        //ToDo here logic what to save under what circumstances
        return userInformationRepository.save(currentUser)
    }

    //ToDo Validate input secure that only intended user can modify their fields
    fun saveUserInformation(user: UserInformation) {

        if (!userInformationRepository.existsById(user.user_information_id!!)) {
            val userMain = getCurrentUser()
            userInformationRepository.save(user)
            userMain.userInformation = userInformationRepository.findByUsername(user.username).get()
            userRepository.save(userMain)
        }

        throw EntityNotFoundException("User with id ${user.user_information_id} does exist")
    }

    fun updateDeac(user: UserInformationDto): UserInformation {
        if (!userInformationRepository.existsById(user.user_information_id!!)) {
            throw EntityNotFoundException("User with id ${user.user_information_id} does not exist")
        }
        return userInformationRepository.save(MappingUtils.convertToEntity(user))
    }

    override fun findAll(): MutableIterable<User> {
        return userRepository.findAll()
    }

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
        val email = SecurityContextHolder.getContext().authentication.name
        return userRepository.findByEmail(email)
            ?: throw EntityNotFoundException("User with email $email does not exist")
    }
}
