package com.invictoprojects.marketplace.dto

import javax.validation.constraints.Email
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull

class UserDto(

    @NotNull
    @Email
    var email: String,

    @NotNull
    var subscribed: Boolean

)
