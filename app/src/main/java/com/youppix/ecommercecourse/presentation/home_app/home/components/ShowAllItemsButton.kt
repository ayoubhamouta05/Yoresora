package com.youppix.ecommercecourse.presentation.home_app.home.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.youppix.ecommercecourse.R
import com.youppix.ecommercecourse.common.Dimens

@Composable
fun BoxScope.ShowAllItemsButton(
    showButton: Boolean,
    onClick: () -> Unit
) {
    AnimatedVisibility(
        visible = showButton,
        enter = fadeIn() + slideInVertically(),
        exit = fadeOut() + slideOutVertically(),
        modifier = Modifier
            .align(Alignment.BottomCenter)
            .padding(bottom = Dimens.BottomBarHeight.plus(Dimens.LargePadding))

    ) {
        Button(
            onClick = {
                onClick()
            },
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.primary.copy(0.9f),
                contentColor = MaterialTheme.colorScheme.background
            )
        ) {
            Text(
                stringResource(id = R.string.seeAllItems),
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }

}