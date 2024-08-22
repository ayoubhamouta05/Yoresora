package com.youppix.ecommercecourse.presentation.home_app.cart.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.youppix.ecommercecourse.R
import com.youppix.ecommercecourse.common.Dimens
import com.youppix.ecommercecourse.common.Dimens.SmallPadding
import com.youppix.ecommercecourse.presentation.home_app.cart.CartEvent
import com.youppix.ecommercecourse.presentation.home_app.cart.CartState

@Composable
fun BottomSection(
    modifier: Modifier = Modifier,
    state: CartState,
    event: (CartEvent) -> Unit,
    onProceedToCheckout: () -> Unit,
) {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.background
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = Dimens.MediumPadding
        ),
        border = BorderStroke(width = 0.5.dp , color = MaterialTheme.colorScheme.primary.copy(alpha = 0.3f)),
        shape = RoundedCornerShape(topEnd = Dimens.MediumPadding , topStart = Dimens.MediumPadding)

    ) {
        Column(
            modifier = modifier
                .padding(
                    vertical = Dimens.MediumPadding,
                    horizontal = Dimens.LargePadding
                )
        ) {
            CodePromoSection(
                codePromoError = state.promoCodeError,
                promoCode = state.promoCode,
                onCodePromoChange = {
                    event(CartEvent.OnPromoCodeChange(it))
                })

            BottomSectionItem(
                title = stringResource(id = R.string.deliveryFee),
                value = state.subTotal.toString()
            )
            BottomSectionItem(
                title = stringResource(id = R.string.subTotal),
                value = state.deliveryFee.toString()
            )
            BottomSectionItem(
                title = stringResource(id = R.string.discount),
                value = state.discount.toString()
            )


            Spacer(
                modifier = Modifier
                    .padding(Dimens.SmallPadding)
                    .fillMaxWidth()
                    .height(1.dp)
                    .background(MaterialTheme.colorScheme.primary.copy(alpha = 0.3f))

            )

            BottomSectionItem(
                title = stringResource(id = R.string.totalCost),
                value = state.totalCost.toString()
            )


            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = Dimens.MediumPadding),
                onClick = {
                    onProceedToCheckout()
                }) {
                Text(
                    modifier = Modifier.padding(vertical = Dimens.ExtraSmallPadding2),
                    text = stringResource(id = R.string.proceedToCheckout),
                    style = MaterialTheme.typography.bodyMedium.copy(
                        fontWeight = FontWeight.Bold
                    )
                )
            }

        }
    }
}


@Composable
fun CodePromoSection(
    codePromoError: String?, promoCode: String,
    onCodePromoChange: (String) -> Unit,
) {
    Row(
        modifier = Modifier
            .padding(horizontal = Dimens.ExtraSmallPadding2)
            .padding(bottom = SmallPadding)
            .border(
                width = 1.dp,
                color = if (codePromoError.isNullOrEmpty())
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
            onClick = { /*TODO*/ }) {
            Text(
                text = stringResource(id = R.string.apply),
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
    AnimatedVisibility(visible = !codePromoError.isNullOrEmpty()) {
        Text(
            modifier = Modifier.padding(
                start = Dimens.MediumPadding.plus(
                    Dimens.ExtraSmallPadding2
                )
            ),
            text = codePromoError ?: "",
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.error
        )
    }
}