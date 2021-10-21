package com.signaltekno.onlinestore.ui

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle

@Composable
fun Home() {
    Text(text = "Welcome", style = MaterialTheme.typography.h1)
}