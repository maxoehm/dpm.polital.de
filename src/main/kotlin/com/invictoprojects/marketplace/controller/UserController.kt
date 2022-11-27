package com.invictoprojects.marketplace.controller

import com.invictoprojects.marketplace.dto.MappingUtils
import com.invictoprojects.marketplace.dto.UserDto
import com.invictoprojects.marketplace.dto.user.UserInformationDto
import com.invictoprojects.marketplace.persistence.model.User
import com.invictoprojects.marketplace.persistence.model.user.UserInformation
import com.invictoprojects.marketplace.service.UserService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpSession

@RestController
@RequestMapping("/api/users")
class UserController(
    private val userService: UserService
) {

    @PutMapping("{id}")
    fun updateUserInfo(@PathVariable id: Long, @RequestBody userDto: UserDto): ResponseEntity<User> {
        val user = MappingUtils.convertToEntity(userDto)
        user.id = id
        val result = userService.update(user)
        return ResponseEntity.ok().body(result)
    }

    @PutMapping()
    fun updateUserInfo(@RequestBody userInfoDto: UserInformationDto): ResponseEntity<User> {
        val userInfo = MappingUtils.convertToEntity(userInfoDto)

        val user: User = userService.getCurrentUser()
        user.userInformation = userInfo
        val result = userService.update(user)
        return ResponseEntity.ok().body(result)
    }

    @PostMapping("/disable/{id}")
    fun disableUser(@PathVariable id: Long): ResponseEntity<String> {
        userService.disableById(id)
        return ResponseEntity.ok().body(String.format("User with id %s disabled", id))
    }
}
