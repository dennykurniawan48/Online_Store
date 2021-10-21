package com.signaltekno.onlinestore.viewmodel

import android.app.Application
import android.content.Context
import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.datastore.core.DataStore
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.signaltekno.onlinestore.model.NetworkResult
import com.signaltekno.onlinestore.model.User.User
import com.signaltekno.onlinestore.repository.AuthRepository
import com.signaltekno.onlinestore.repository.AuthStoreRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewmodel @Inject constructor(
    application: Application,
    private val authStore: AuthStoreRepository,
    private val authRepository: AuthRepository
): AndroidViewModel(application) {
    var isLoggedIn = mutableStateOf(false)
    var loginData: MutableLiveData<NetworkResult<User>> = MutableLiveData(NetworkResult.Idle())

    fun login(email: String, password: String){
        loginData.value = NetworkResult.Loading()
        viewModelScope.launch {
            loginData.value = authRepository.login(email = email, password = password)
        }
    }
}