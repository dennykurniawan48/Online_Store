package com.signaltekno.onlinestore.repository

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

private val Context.dataStore by preferencesDataStore(name = "auth")
@ViewModelScoped
class AuthStoreRepository @Inject constructor(@ApplicationContext private val context: Context){

    private val dataStore = context.dataStore

    val flowToken: Flow<String> = context.dataStore.data
        .map { preferences ->
            // No type safety.
            preferences[tokenKey] ?: ""
        }

    suspend fun incrementCounter(token: String) {
        context.dataStore.edit { settings ->
            settings[tokenKey] = token
        }
    }

    companion object PreferencesKey{
        val tokenKey = stringPreferencesKey("_token")
    }
}