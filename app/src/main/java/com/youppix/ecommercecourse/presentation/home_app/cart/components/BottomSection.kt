package com.youppix.ecommercecourse.presentation.home_app.cart.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.youppix.ecommercecourse.R
import com.youppix.ecommercecourse.common.Dimens
import com.youppix.ecommercecourse.common.Dimens.ExtraSmallPadding2
import com.youppix.ecommercecourse.common.Dimens.MediumPadding
import com.youppix.ecommercecourse.common.Dimens.SmallPadding
import com.youppix.ecommercecourse.common.Dimens.SocialMediaItemSize
import com.youppix.ecommercecourse.presentation.home_app.cart.CartEvent
import com.youppix.ecommercecourse.presentation.home_app.cart.CartState

@Stable
@Composable
fun BottomSection(
    modifier: Modifier = Modifier,
    state: CartState,
    event: (CartEvent) -> Unit,
    onProceedToCheckout: () -> Unit,
) {

    var showAllDetails by remember {
        mutableStateOf(true)
    }

    Box(modifier = Modifier) {
        Card(
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.background
            ),
            elevation = CardDefaults.cardElevation(
                defaultElevation = Dimens.MediumPadding
            ),
            border = BorderStroke(
                width = 0.5.dp,
                color = MaterialTheme.colorScheme.primary.copy(alpha = 0.3f)
            ),
            shape = RoundedCornerShape(
                topEnd = Dimens.MediumPadding,
                topStart = Dimens.MediumPadding
            )

        ) {
            Column(
                modifier = modifier
                    .padding(
                        vertical = Dimens.MediumPadding,
                        horizontal = Dimens.LargePadding
                    ).padding(top = SmallPadding)
            ) {
                CodePromoSection(
                    codePromoError = state.promoCodeError,
                    promoCode = state.promoCode,
                    onCodePromoChange = {
                        event(CartEvent.OnPromoCodeChange(it))
                    })

                AnimatedVisibility(visible = showAllDetails ){
                    BottomSectionItem(
                        title = stringResource(id = R.string.subTotal),
                        value = state.subTotal.toString()
                    )
                }
                AnimatedVisibility(visible = showAllDetails ){
                    BottomSectionItem(
                        title = stringResource(id = R.string.deliveryFee),
                        value = state.deliveryFee.toString()
                    )
                }
                AnimatedVisibility(visible = showAllDetails ){
                    BottomSectionItem(
                        title = stringResource(id = R.string.discount),
                        value = state.discount.toString()
                    )
                }
                AnimatedVisibility(visible = showAllDetails ){
                    Spacer(
                        modifier = Modifier
                            .padding(Dimens.SmallPadding)
                            .fillMaxWidth()
                            .height(1.dp)
                            .background(MaterialTheme.colorScheme.primary.copy(alpha = 0.3f))
                    )
                }

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

        Icon(
            imageVector = Icons.Default.ArrowDropDown,
            contentDescription = null,
            modifier = Modifier
                .size(SocialMediaItemSize.minus(SmallPadding))
                .rotate(if (showAllDetails) 0f else 180f)
                .align(Alignment.TopCenter)
                .clip(CircleShape)
                .clickable { showAllDetails = !showAllDetails }
        )
    }

}