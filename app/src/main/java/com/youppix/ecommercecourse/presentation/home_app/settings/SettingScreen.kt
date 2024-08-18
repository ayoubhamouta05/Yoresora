package com.youppix.ecommercecourse.presentation.home_app.settings

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen

class SettingScreen : Screen {
    @Composable
    override fun Content() {
        Text(text = "Settings", fontSize = 30.sp)
    }
}