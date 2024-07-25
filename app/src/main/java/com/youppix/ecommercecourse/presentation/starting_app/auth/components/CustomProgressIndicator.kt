package com.youppix.ecommercecourse.presentation.starting_app.auth.components

import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun CustomProgressIndicator(
    show: Boolean,
    color: Color = MaterialTheme.colorScheme.primary,
    strokeWidth: Dp = 4.dp
) {
    if (show) {
        CircularProgressIndicator(
            color = color,
            strokeWidth = strokeWidth,
        )
    }
}