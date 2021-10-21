package com.signaltekno.onlinestore.model.validationerror.login

import kotlinx.serialization.Serializable

@Serializable
data class Error(
    val email: List<String>? = null,
    val password: List<String>? = null
)