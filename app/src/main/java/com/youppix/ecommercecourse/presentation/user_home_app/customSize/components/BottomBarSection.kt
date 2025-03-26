package com.youppix.ecommercecourse.presentation.user_home_app.customSize.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import com.youppix.ecommercecourse.R
import com.youppix.ecommercecourse.common.Dimens
import com.youppix.ecommercecourse.presentation.user_home_app.customSize.CustomSizeEvent
import com.youppix.ecommercecourse.presentation.user_home_app.customSize.CustomSizeState

@Composable
fun BottomBarSection(state : CustomSizeState, event : (CustomSizeEvent)-> Unit) {
    val context = LocalContext.current
    Row(
        Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.background)
            .padding(horizontal = Dimens.MediumPadding)
            .padding(bottom = Dimens.MediumPadding)
            ,
        horizontalArrangement = Arrangement.spacedBy(Dimens.SmallPadding)
    ) {
        Button(
            onClick = { event(CustomSizeEvent.OnReset) },
            shape = CircleShape,
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.09f)
            ),
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = stringResource(id = R.string.resetFilter),
                style = MaterialTheme.typography.bodyMedium.copy(
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.primary
                ),
            )
        }

        Button(
            onClick = { event(CustomSizeEvent.OnConfirm(state , context)) },
            shape = CircleShape,
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.primary
            ),
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = stringResource(id = R.string.confirm),
                style = MaterialTheme.typography.bodyMedium.copy(
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.background
                ),
            )
        }

    }
}