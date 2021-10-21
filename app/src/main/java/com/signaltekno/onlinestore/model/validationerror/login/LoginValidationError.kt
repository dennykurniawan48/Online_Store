package com.signaltekno.onlinestore.model.validationerror.login

import kotlinx.serialization.Serializable

@Serializable
data class LoginValidationError(
    val code: Int,
    val error: Error
)