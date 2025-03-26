package com.youppix.ecommercecourse.presentation.user_home_app.cart.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.youppix.ecommercecourse.R
import com.youppix.ecommercecourse.common.Dimens
import java.util.Locale

@Composable
fun CodePromoSection(
    codePromoError: String?,
    promoCode: String,
    onCheckCodePromo: (String) -> Unit,
    onCodePromoChange: (String) -> Unit,
) {
    val isArabic=  Locale.getDefault().language == "ar"
    Column() {
        Row(
            modifier = Modifier
                .padding(horizontal = Dimens.ExtraSmallPadding2)
                .padding(bottom = Dimens.SmallPadding)
                .border(
                    width = 1.dp,
                    color =
                    if (codePromoError == null) {
                        Color.Green.copy(alpha = 0.5f)
                    } else if (codePromoError.isEmpty())
                        MaterialTheme.colorScheme.primary.copy(alpha = 0.5f)
                    else MaterialTheme.colorScheme.error.copy(
                        alpha = 0.5f
                    ),
                    shape = CircleShape
                ),
            verticalAlignment = Alignment.CenterVertically
        ) {


            BasicTextField(
                modifier = Modifier
                    .weight(1f)
                    .padding(start = Dimens.MediumPadding),
                value = promoCode,
                onValueChange = { value ->
                    onCodePromoChange(value)
                },
                singleLine = true,
                textStyle = MaterialTheme.typography.bodyMedium,
                decorationBox = { innerTextField ->
                    if (promoCode.isEmpty()) {
                        Text(
                            text = stringResource(id = R.string.promoCode),
                            style = MaterialTheme.typography.bodyMedium.copy(
                                fontWeight = FontWeight.Normal
                            )
                        )
                    }
                    innerTextField()
                },
            )
            Spacer(modifier = Modifier.width(Dimens.MediumPadding))
            Button(
                modifier = Modifier.padding(Dimens.ExtraSmallPadding2),
                onClick = { onCheckCodePromo(promoCode) }) {
                Text(
                    text = stringResource(id = R.string.apply),
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
        AnimatedVisibility(visible = !codePromoError.isNullOrEmpty()) {
            Text(
                modifier = Modifier
                    .padding(
                        start = Dimens.MediumPadding.plus(
                            Dimens.ExtraSmallPadding2
                        )
                    )
                    .offset(y = (-5).dp),
                text = if(isArabic)
                    stringResource(R.string.invalidPromoCodeErrorMessage)
                else
                    codePromoError ?: "",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.error
            )
        }
    }
}