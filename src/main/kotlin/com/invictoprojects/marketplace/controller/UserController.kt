package com.invictoprojects.marketplace.controller

import com.invictoprojects.marketplace.dto.MappingUtils
import com.invictoprojects.marketplace.dto.UserDto
import com.invictoprojects.marketplace.persistence.model.User
import com.invictoprojects.marketplace.persistence.model.user.UserInformationMin
import com.invictoprojects.marketplace.service.impl.user.UserService
import org.springframework.context.annotation.Role
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

//ToDo: check if class is for everybody accessible
@RestController
@RequestMapping("/api/users")
class UserController(
    private val userService: UserService
) {

    //ToDo: Security check here
    @PutMapping("{id}")
    fun updateUserInfo(@PathVariable id: Long, @RequestBody userDto: UserDto): ResponseEntity<User> {
        val user = MappingUtils.convertToEntity(userDto)
        user.id = id
        val result = userService.update(user)
        return ResponseEntity.ok().body(result)
    }

    @PostMapping("/disable/{id}")
    fun disableUser(@PathVariable id: Long): ResponseEntity<String> {
        userService.disableById(id)
        return ResponseEntity.ok().body(String.format("User with id %s disabled", id))
    }

    @GetMapping("/get/usi")
    fun getUserInformation(): ResponseEntity<UserInformationMin> {
        val usi: UserInformationMin = UserInformationMin.fromUserInformation(userService.getCurrentUser().userInformation)
        return ResponseEntity.ok().body(usi)
    }
}
