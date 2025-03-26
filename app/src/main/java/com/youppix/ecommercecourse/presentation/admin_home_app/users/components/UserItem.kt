package com.youppix.ecommercecourse.presentation.admin_home_app.users.components

import android.widget.Space
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import com.youppix.ecommercecourse.R
import com.youppix.ecommercecourse.common.Dimens.SmallPadding
import com.youppix.ecommercecourse.domain.model.user.User

@Composable
fun UserItem(modifier: Modifier, user: User) {

    Column(
        modifier = modifier,
//        verticalArrangement = Arrangement.spacedBy(SmallPadding)
    ) {
        Row {
            Text(
                text = stringResource(R.string.userName) + " : ",
                style = MaterialTheme.typography.bodyMedium.copy(
                    fontWeight = FontWeight.Bold
                )
            )

            Text(
                text = user.userName,
                style = MaterialTheme.typography.bodyMedium
            )

            Spacer(Modifier.weight(1f))

            Text(
                text = stringResource(R.string.numberOfOrders) + " : ",
                style = MaterialTheme.typography.bodyMedium.copy(
                    fontWeight = FontWeight.Bold
                )
            )
            Text(
                text = "10",
                style = MaterialTheme.typography.bodyMedium
            )
        }

        Row {
            Text(
                text = stringResource(R.string.email) + " : ",
                style = MaterialTheme.typography.bodySmall.copy(
                    fontWeight = FontWeight.Bold
                )
            )

            Text(
                text = user.userEmail,
                style = MaterialTheme.typography.bodySmall
            )
        }
        Row {
            Text(
                text = stringResource(R.string.phone) + " : ",
                style = MaterialTheme.typography.bodySmall.copy(
                    fontWeight = FontWeight.Bold
                )
            )

            Text(
                text = user.userPhone,
                style = MaterialTheme.typography.bodySmall
            )
        }
    }


}