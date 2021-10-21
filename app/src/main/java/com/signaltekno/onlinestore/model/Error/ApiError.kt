package com.signaltekno.onlinestore.model.Error

import kotlinx.serialization.Serializable

@Serializable
data class ApiError(
    val code: Int,
    val error: String
)