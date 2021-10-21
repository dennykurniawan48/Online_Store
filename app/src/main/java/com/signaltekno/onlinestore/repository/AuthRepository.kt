package com.signaltekno.onlinestore.repository

import com.signaltekno.onlinestore.dataremote.AuthServiceImpl
import com.signaltekno.onlinestore.model.NetworkResult
import com.signaltekno.onlinestore.model.User.User
import dagger.hilt.android.scopes.ViewModelScoped
import io.ktor.client.*
import io.ktor.client.engine.android.*
import io.ktor.client.features.json.*
import javax.inject.Inject

@ViewModelScoped
class AuthRepository @Inject constructor() {
    val client = HttpClient(Android){
        install(JsonFeature)
    }

    suspend fun login(email:String, password: String): NetworkResult<User>?{
        val data = AuthServiceImpl(httpClient = client).login(email, password)
        return data
    }
}