package com.youppix.ecommercecourse.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import com.youppix.ecommercecourse.R
import com.youppix.ecommercecourse.common.Dimens

@Composable
fun CustomButton(text: String, modifier: Modifier = Modifier, onClick: () -> Unit) {

    Button(
        onClick = {
            onClick()
        },
        modifier = modifier
        .fillMaxWidth(0.3f),
         shape = RoundedCornerShape(10),
        colors = ButtonDefaults.buttonColors(
            containerColor =  MaterialTheme.colorScheme.primary
        )

    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.bodyMedium,
            maxLines = 1
        )

    }
}