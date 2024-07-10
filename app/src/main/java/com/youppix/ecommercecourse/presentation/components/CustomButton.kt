package com.youppix.ecommercecourse.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import com.youppix.ecommercecourse.R
import com.youppix.ecommercecourse.common.Dimens

@Composable
fun CustomButton(text: String, modifier: Modifier = Modifier , onClick : ()->Unit) {

    Button(modifier = modifier
        .background(color = MaterialTheme.colorScheme.primary, shape = RoundedCornerShape(10)),
        onClick = {
            onClick()
        }
    ) {
        Text(text = text,
            fontWeight = FontWeight.Bold
        )

    }
}