package com.signaltekno.onlinestore

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.datastore.preferences.preferencesDataStore
import com.signaltekno.onlinestore.ui.Home
import com.signaltekno.onlinestore.ui.Login
import com.signaltekno.onlinestore.ui.theme.OnlineStoreTheme
import com.signaltekno.onlinestore.viewmodel.AuthViewmodel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val viewModel: AuthViewmodel by viewModels()
        setContent {
            OnlineStoreTheme {
                val isLoggedIn by viewModel.isLoggedIn
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    if(!isLoggedIn){
                        Login(viewModel)
                    }else{
                        Home()
                    }
                }
            }
        }
    }
}
