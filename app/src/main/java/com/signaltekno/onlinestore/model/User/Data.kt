package com.signaltekno.onlinestore.model.User

import kotlinx.serialization.Serializable

@Serializable
data class Data(
    val blocked: Int,
    val created_at: String,
    val email: String,
    val first_name: String,
    val id: Int,
    val is_seller: Int,
    val isverified: Boolean,
    val last_name: String,
    val remember_token: String?,
    val token: String,
    val updated_at: String
)