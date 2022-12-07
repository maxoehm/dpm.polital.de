package com.invictoprojects.marketplace.dto

import com.invictoprojects.marketplace.persistence.model.user.UserInformation
import com.invictoprojects.marketplace.persistence.model.user.UserInformationMin
import java.time.Instant

class AuthenticationResponse(
    val authenticationToken: String,
    val refreshToken: String,
    val expiresAt: Instant,
    val email: String,
    val iUId: Long,
    val userInformation: UserInformationMin
)
