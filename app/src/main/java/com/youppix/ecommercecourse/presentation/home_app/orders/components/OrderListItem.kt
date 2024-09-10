package com.youppix.ecommercecourse.presentation.home_app.orders.components

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CloudDone
import androidx.compose.material.icons.filled.Dangerous
import androidx.compose.material.icons.filled.Error
import androidx.compose.material.icons.filled.Timelapse
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.youppix.ecommercecourse.R
import com.youppix.ecommercecourse.common.Constant.formatDate
import com.youppix.ecommercecourse.common.Dimens
import com.youppix.ecommercecourse.common.Dimens.ExtraSmallPadding
import com.youppix.ecommercecourse.common.Dimens.LargePadding
import com.youppix.ecommercecourse.common.Dimens.MediumPadding
import com.youppix.ecommercecourse.common.Dimens.SmallPadding
import com.youppix.ecommercecourse.common.Dimens.SocialMediaItemSize
import com.youppix.ecommercecourse.domain.model.orders.Order
import com.youppix.ecommercecourse.presentation.home_app.orders.OrdersType


@Stable
@Composable
fun OrderListItem(modifier: Modifier = Modifier, order: Order, onDetailsClick: () -> Unit) {

    val brush = Brush.linearGradient(
        listOf(
            MaterialTheme.colorScheme.background,
            MaterialTheme.colorScheme.primary
        ),
        start = Offset(0f, 1000f),
        end = Offset(2000f, 2500f)
    )
    Card(
        modifier = modifier
            .fillMaxWidth()
           ,
        shape = RoundedCornerShape(26.dp),
        elevation = CardDefaults.elevatedCardElevation(
            defaultElevation = 4.dp,
        ),
        colors = CardDefaults.elevatedCardColors(
            containerColor = MaterialTheme.colorScheme.surface,
        )
    ) {
        Box(Modifier.fillMaxSize().animateContentSize()) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        brush = brush
                    )
                    .padding(horizontal = MediumPadding, vertical = SmallPadding)
            ) {

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(SmallPadding)
                ) {
                    Text(
                        text = stringResource(id = R.string.orderId, order.ordersId),
                        style = MaterialTheme.typography.bodyMedium.copy(
                            fontWeight = FontWeight.Bold,
                            textAlign = TextAlign.Start
                        ),
                        modifier = Modifier
                            .weight(1f)
                            .offset(y = (-1.5).dp),
                        maxLines = 1 ,
                        overflow = TextOverflow.Ellipsis
                    )

                }
                Text(
                    text =formatDate(order.ordersDate) ,
                    style = MaterialTheme.typography.labelSmall.copy(
                        color = colorResource(id = R.color.body),
                        textAlign = TextAlign.Center
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .offset(y = (-1.5).dp),
                )
                Spacer(
                    modifier = Modifier
                        .padding(vertical = SmallPadding)
                        .height(0.5.dp)
                        .fillMaxWidth()
                        .offset(y = (-1.5).dp)
                        .background(MaterialTheme.colorScheme.primary.copy(alpha = 0.5f))
                )


                Text(
                    text = stringResource(id = R.string.numberOfItems, order.numberOfItems),
                    style = MaterialTheme.typography.bodySmall.copy(
                        color = colorResource(id = R.color.body)
                    ),
                    modifier = Modifier.offset(y = (-3).dp)
                )

                Row(
                    Modifier
                        .offset(y = (-3).dp)
                        .fillMaxWidth(0.85f)) {
                    Text(
                        text = stringResource(id = R.string.deliveryMethod)+": ",
                        style = MaterialTheme.typography.bodySmall.copy(
                            color = colorResource(id = R.color.body)
                        ),
                    )
                    Text(
                        text = if (order.deliveryMethod == 1) stringResource(id = R.string.homeDelivery)
                        else stringResource(id = R.string.pickupPointDelivery),
                        style = MaterialTheme.typography.bodySmall.copy(
                            color = colorResource(id = R.color.body)
                        )
                    )
                }

                Text(
                    text = stringResource(id = R.string.orderItems, order.ordersDescription),
                    style = MaterialTheme.typography.bodySmall.copy(
                        color = colorResource(id = R.color.body)
                    ),
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.offset(y = (-3).dp)
                )

                Spacer(
                    modifier = Modifier
                        .padding(vertical = SmallPadding)
                        .height(0.5.dp)
                        .fillMaxWidth()
                        .offset(y = (-1.5).dp)
                        .background(MaterialTheme.colorScheme.primary.copy(alpha = 0.5f))
                )

                Row(Modifier.offset(y = (-3).dp)) {
                    Text(
                        text = stringResource(id = R.string.price),
                        style = MaterialTheme.typography.bodyMedium.copy(
                            color = colorResource(id = R.color.body)
                        )
                    )
                    Text(
                        text = stringResource(id = R.string.prixValue, order.ordersAmount),
                        style = MaterialTheme.typography.bodyMedium.copy(
                            fontWeight = FontWeight.Bold
                        ),
                        modifier = Modifier.weight(1f)
                    )

                    Card(
                        modifier = Modifier
                            .clickable { onDetailsClick() },
                        shape = RoundedCornerShape(8.dp),
                        colors = CardDefaults.cardColors(MaterialTheme.colorScheme.primary)
                    ) {

                        Text(
                            text = stringResource(id = R.string.details),
                            color = MaterialTheme.colorScheme.background,
                            style = MaterialTheme.typography.bodySmall.copy(
                                fontWeight = FontWeight.SemiBold
                            ),
                            textAlign = TextAlign.Center,
                            modifier = Modifier.padding(
                                horizontal = Dimens.SmallPadding,
                                vertical = Dimens.ExtraSmallPadding2
                            )
                        )
                    }
                }
            }
            Icon(
                imageVector = when (order.ordersStatus) {
                    "completed" -> Icons.Filled.CloudDone
                    "paid" -> Icons.Default.Timelapse
                    else -> Icons.Default.Error
                }, contentDescription = null,
                Modifier
                    .padding(horizontal = MediumPadding)
                    .size(SocialMediaItemSize)
                    .align(Alignment.CenterEnd)
                    .offset(y = -SmallPadding),
                tint = MaterialTheme.colorScheme.onBackground

            )
        }
    }

}