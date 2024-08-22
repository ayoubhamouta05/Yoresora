package com.youppix.ecommercecourse.presentation.home_app.checkout

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import cafe.adriel.voyager.core.screen.Screen

class CheckoutScreen : Screen {
    @Composable
    override fun Content() {
        Text(text = "Checkout", style = MaterialTheme.typography.displayMedium)
    }
}