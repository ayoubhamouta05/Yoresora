package com.youppix.ecommercecourse.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.youppix.ecommercecourse.R
import java.util.Locale


@Composable
fun CustomDialog(
    title: String,
    message: String,
    showDialog: Boolean,
    onConfirmRequest: () -> Unit,
    onDismissRequest: () -> Unit,
) {

    if (showDialog) {

        Dialog(
            onDismissRequest = onDismissRequest,
            properties = DialogProperties(
                usePlatformDefaultWidth = false
            )
        ) {
            Column(
                modifier = Modifier
                    .padding(16.dp)
                    .width(300.dp)
                    .wrapContentHeight()
                    .shadow(8.dp, shape = RoundedCornerShape(16.dp))
                    .clip(RoundedCornerShape(16.dp))
                    .background(
                        MaterialTheme.colorScheme.surface,
                    ),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Title
                Text(
                    title,
                    modifier = Modifier.fillMaxWidth().padding(top = 16.dp),
                    color = MaterialTheme.colorScheme.primary,
                    fontWeight = FontWeight.Bold,
                    style = MaterialTheme.typography.headlineMedium,
                    textAlign = TextAlign.Center
                )
                // Message
                Text(
                    message,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp , horizontal = 16.dp),
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.bodyMedium.copy(
                        fontWeight = FontWeight.Normal
                    )
                )

                Row(
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    // Cancel button
                    Button(
                        onClick = { onDismissRequest() },
                        modifier = Modifier
                            .background(
                                MaterialTheme.colorScheme.surface,
                                shape = RoundedCornerShape(35.dp)
                            )
                            .height(55.dp)
                            .weight(1f),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.Transparent
                        ),
                    ) {
                        Text(
                            text = stringResource(id = R.string.cancel),
                            color = MaterialTheme.colorScheme.primary,
                            fontWeight = FontWeight.SemiBold,
                            style = MaterialTheme.typography.titleMedium
                        )
                    }

                    // Action button
                    Button(
                        onClick = {
                            onConfirmRequest()
                        },
                        modifier = Modifier
                            .background(
                                MaterialTheme.colorScheme.primary,
                                shape = RoundedCornerShape(35.dp)
                            )
                            .height(55.dp)
                            .weight(1f),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.Transparent,
                        ),
                    ) {
                        Text(
                            text = stringResource(id = R.string.ok),
                            fontWeight = FontWeight.SemiBold,
                            style = MaterialTheme.typography.titleMedium
                        )
                    }

                }
            }

        }
    }

}


@Composable
fun LeavingAppDialog( showDialog: Boolean , onConfirmRequest: () -> Unit , onDismissRequest: () -> Unit){
   val currentLang = Locale.getDefault().language
    CompositionLocalProvider(
        if (currentLang != "ar")
            LocalLayoutDirection provides LayoutDirection.Ltr
        else LocalLayoutDirection provides LayoutDirection.Rtl
    )
    {
        CustomDialog(
            title = stringResource(id = R.string.leaveTheApp),
            message = stringResource(id = R.string.doYouReallyWantToLeave),
            showDialog = showDialog,
            onDismissRequest = {
                onDismissRequest()
            },
            onConfirmRequest = {
                onConfirmRequest()
            }
        )
    }
}