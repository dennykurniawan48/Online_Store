package com.signaltekno.onlinestore.dataremote

import android.util.Log
import com.signaltekno.onlinestore.model.Constant
import com.signaltekno.onlinestore.model.Error.ApiError
import com.signaltekno.onlinestore.model.NetworkResult
import com.signaltekno.onlinestore.model.User.User
import com.signaltekno.onlinestore.model.validationerror.login.LoginValidationError
import io.ktor.client.*
import io.ktor.client.features.*
import io.ktor.client.request.*
import io.ktor.utils.io.*
import kotlinx.serialization.*
import kotlinx.serialization.json.*

class AuthServiceImpl(
    private val httpClient: HttpClient
) : AuthService{
    override suspend fun login(email: String, password: String): NetworkResult<User>? {
        return try{
            val response: User = httpClient.post{
                url("${Constant.BASE_URL}login")
                parameter("email", email)
                parameter("password", password)
            }
            NetworkResult.Success(response)
        }catch (e: RedirectResponseException){
            return getError(e.response.content)
        }catch (e: ClientRequestException){
            if (e.response.status.value == 422) {
                try {
                    e.response.content.readUTF8Line()?.let {
                        val err = Json.decodeFromString<LoginValidationError>(it)
                        if (err.error.email != null) {
                            return NetworkResult.Error(err.error.email[0])
                        } else {
                            return NetworkResult.Error(err.error.password?.get(0))
                        }
                    }
                }catch (e: Exception){
                    return NetworkResult.Error("Unexpected Error")
                }
            }
            return getError(e.response.content)
        }catch (e: ServerResponseException){
            return getError(e.response.content)
        }catch (e: Exception){
            NetworkResult.Error(e.message)
        }
    }

    suspend fun getError(responseContent: ByteReadChannel): NetworkResult.Error<User> {
        responseContent.readUTF8Line()?.let {
            Log.d("Error api", it)
            try{
                val err = Json.decodeFromString<ApiError>(it)
                return NetworkResult.Error(err.error)
            }catch(e: Exception){
                return NetworkResult.Error("Unexpected error")
            }
        }
        return NetworkResult.Error("Unexpected error")
    }

}