package com.youppix.ecommercecourse.presentation.login

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import cafe.adriel.voyager.core.screen.Screen

class LoginScreen() : Screen {
    @Composable
    override fun Content() {
        Text(text = "Login Screen" , modifier = Modifier.fillMaxSize(), textAlign = TextAlign.Center)
    }
}