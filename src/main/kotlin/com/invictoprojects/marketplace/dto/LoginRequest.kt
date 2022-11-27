package com.invictoprojects.marketplace.dto

import javax.validation.constraints.NotNull

class LoginRequest(
    @NotNull var identifier: String,
    @NotNull var password: String
)
