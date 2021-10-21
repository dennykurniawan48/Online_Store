package com.signaltekno.onlinestore.ui

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.signaltekno.onlinestore.R
import com.signaltekno.onlinestore.model.NetworkResult
import com.signaltekno.onlinestore.ui.theme.ButtonGreen
import com.signaltekno.onlinestore.viewmodel.AuthViewmodel

@Composable
fun Login(
    viewmodel: AuthViewmodel
) {
    fun validateEmail(text: String) {

    }

    fun validatePassword(text: String) {

    }
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ){
        var username by remember{ mutableStateOf("") }
        var password by remember{ mutableStateOf("") }
        var showPassword by remember{ mutableStateOf(false)}
        val loginResult by viewmodel.loginData.observeAsState()

        if(loginResult is NetworkResult.Loading){
            Log.d("Status", "Loading data")
        }else if(loginResult is NetworkResult.Error){
            Log.d("Status", loginResult!!.message.toString())
        }else if(loginResult is NetworkResult.Success){
            Log.d("Status", loginResult!!.data.toString())
        }else if(loginResult is NetworkResult.Idle){
            Log.d("Status", "Idle")
        }

        var iconVisibilty = if(showPassword) R.drawable.ic_visibility else R.drawable.ic_visibility_off

        Image(
            painter = painterResource(id = R.drawable.shop),
            modifier = Modifier
                .size(128.dp)
                .clip(
                    CircleShape
                ),
            contentDescription = "Logo Shop",
            contentScale = ContentScale.Crop
        )
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp, start = 24.dp, end = 24.dp),
            value = username,
            onValueChange = {
                username = it
            },
            label = {
                Text(text = "Email")
            },
            placeholder = { Text(text = "Email") }
        )
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp, start = 24.dp, end = 24.dp),
            value = password,
            onValueChange = {
                password = it
            },
            trailingIcon = {
               IconButton(onClick = { showPassword = !showPassword }) {
                   Icon(painter = painterResource(id = iconVisibilty), contentDescription = "Change Visibility")
               }
            },
            label = {
                Text(text = "Password")
            },
            visualTransformation = if(showPassword) VisualTransformation.None else PasswordVisualTransformation()
        )
        if(loginResult is NetworkResult.Error){
            Text(modifier = Modifier.padding(4.dp),text = if(loginResult!!.message != null) loginResult!!.message.toString() else "", color = Color.Red)
        }
        Button(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp, start = 24.dp, end = 24.dp),
            colors = ButtonDefaults.buttonColors(backgroundColor = ButtonGreen),
            onClick = { viewmodel.login(email = username, password = password) }
        ) {
            Text(
                text = "Login",
                modifier = Modifier.padding(4.dp),
                color = Color.White
            )
        }
    }
}
