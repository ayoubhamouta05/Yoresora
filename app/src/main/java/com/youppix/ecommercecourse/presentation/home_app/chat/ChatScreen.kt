package com.youppix.ecommercecourse.presentation.home_app.chat

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.core.screen.Screen


class ChatScreen() : Screen {

    @Composable
    override fun Content() {
        Spacer(modifier = Modifier.fillMaxSize().background(MaterialTheme.colorScheme.primary))
        Text("Chat")
    }
}