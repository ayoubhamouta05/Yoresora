package com.youppix.ecommercecourse.presentation.user_home_app.address.components

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
import androidx.compose.runtime.Stable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import com.youppix.ecommercecourse.R
import com.youppix.ecommercecourse.common.Dimens

@Stable
@Composable
fun UpsertCancelButtonsRow(inserting : Boolean,
                           onConfirmCLick: () -> Unit,
                           onCancelClick: () -> Unit) {

    Row(
        Modifier
            .fillMaxWidth()
            .padding(horizontal = Dimens.MediumPadding),
        horizontalArrangement = Arrangement.spacedBy(Dimens.SmallPadding)
    ) {
        Button(
            onClick = { onCancelClick() }, shape = CircleShape, colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.09f)
            ), modifier = Modifier.weight(1f)
        ) {
            Text(
                text = stringResource(id = R.string.cancel),
                style = MaterialTheme.typography.bodyMedium.copy(
                    fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.primary
                ),
            )
        }

        Button(
            onClick = { onConfirmCLick() },
            shape = CircleShape,
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.primary
            ),
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = if (inserting) stringResource(id = R.string.add) else stringResource(id = R.string.update),
                style = MaterialTheme.typography.bodyMedium.copy(
                    fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.background
                ),
            )
        }

    }
}
