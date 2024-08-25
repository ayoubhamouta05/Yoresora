package com.youppix.ecommercecourse.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.youppix.ecommercecourse.common.Dimens.MediumPadding
import com.youppix.ecommercecourse.common.Dimens.SmallPadding

@Composable
fun CustomCircularProgress(modifier: Modifier = Modifier, isLoading: Boolean) {

    if (isLoading) {
        Card(
            modifier = modifier ,
            elevation = CardDefaults.cardElevation(
                defaultElevation = 8.dp
            ),
            shape = RoundedCornerShape(SmallPadding)
        ) {
            CircularProgressIndicator(
                modifier = Modifier
                    .background(
                        color = MaterialTheme.colorScheme.background,
                        shape = CircleShape.copy(CornerSize(15.dp))
                    )
                    .padding(10.dp), color = MaterialTheme.colorScheme.primary
            )
        }

    }
}