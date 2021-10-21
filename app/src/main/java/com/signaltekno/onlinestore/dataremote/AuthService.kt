package com.signaltekno.onlinestore.dataremote

import com.signaltekno.onlinestore.model.NetworkResult
import com.signaltekno.onlinestore.model.User.User

interface AuthService {
    suspend fun login(email: String, password: String): NetworkResult<User>?
}