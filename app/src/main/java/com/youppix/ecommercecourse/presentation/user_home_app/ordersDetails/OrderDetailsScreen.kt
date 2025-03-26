package com.youppix.ecommercecourse.presentation.user_home_app.ordersDetails

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.hilt.getNavigatorScreenModel
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.youppix.ecommercecourse.R
import com.youppix.ecommercecourse.common.Constant
import com.youppix.ecommercecourse.common.Dimens
import com.youppix.ecommercecourse.common.Dimens.MediumPadding
import com.youppix.ecommercecourse.common.Dimens.SmallPadding
import com.youppix.ecommercecourse.domain.model.orders.Order
import com.youppix.ecommercecourse.domain.model.orders.toCartData
import com.youppix.ecommercecourse.presentation.components.CustomCircularProgress
import com.youppix.ecommercecourse.presentation.components.CustomTopAppBar
import com.youppix.ecommercecourse.presentation.user_home_app.address.components.ShippingAddressItem
import com.youppix.ecommercecourse.presentation.user_home_app.checkout.components.CartItemOfOrder
import com.youppix.ecommercecourse.presentation.user_home_app.home.HomeScreen
import java.util.Locale

data class OrderDetailsScreen(val order: Order) : Screen {
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        val viewModel: OrderDetailsViewModel = navigator.getNavigatorScreenModel()
        val state by viewModel.state
        val isArabic = Locale.getDefault().language == "ar"

        LaunchedEffect(Unit) {
            viewModel.onEvent(OrderDetailsEvent.GetOrderDetails(order.ordersId))
        }



        Scaffold(
            modifier = Modifier.fillMaxSize(),
            topBar = {
                CustomTopAppBar(title = stringResource(id = R.string.orderDetails),
                    isArabic = isArabic,
                    onBackClicked = {
                        if (navigator.canPop) {
                            navigator.pop()
                        } else {
                            navigator.replace(HomeScreen())
                        }
                    })
            },
        ) { innerPadding ->
            if (!state.isLoading) {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(innerPadding)
                        .padding(horizontal = Dimens.SmallPadding)
                ) {
                    item {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(
                                    horizontal = Dimens.MediumPadding,
                                    vertical = Dimens.SmallPadding
                                )
                        ) {

                            Column(
                                modifier = Modifier.fillMaxWidth(),
                            ) {
                                Text(
                                    text = stringResource(id = R.string.orderId, ""),
                                    style = MaterialTheme.typography.titleSmall,
                                    textAlign = TextAlign.Start
                                )
                                Text(
                                    text = order.ordersId,
                                    style = MaterialTheme.typography.bodyMedium.copy(
                                        fontWeight = FontWeight.Bold,
                                        textAlign = TextAlign.Start,
                                        color = colorResource(id = R.color.body)
                                    )
                                )

                            }

                            Text(
                                text = stringResource(
                                    id = R.string.at,
                                    Constant.formatDate(order.ordersDate)
                                ),
                                style = MaterialTheme.typography.labelSmall.copy(
                                    color = colorResource(id = R.color.body),
                                    textAlign = TextAlign.Start
                                ),
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(top = SmallPadding)

                            )

                            Spacer(
                                modifier = Modifier
                                    .padding(vertical = Dimens.MediumPadding)
                                    .height(0.5.dp)
                                    .fillMaxWidth()
                                    .background(MaterialTheme.colorScheme.primary.copy(alpha = 0.5f))
                            )

                            Text(
                                text = stringResource(id = R.string.ShippingAddress),
                                style = MaterialTheme.typography.titleSmall,
                                textAlign = TextAlign.Start
                            )

                            ShippingAddressItem(
                                addressName = order.ordersAddressName,
                                addressInformation = order.ordersAddressInformation,
                                modifier = Modifier.padding(vertical = SmallPadding)
                            )

                            Text(
                                text = stringResource(
                                    id = R.string.numberOfItems,
                                    order.numberOfItems
                                ),
                                style = MaterialTheme.typography.bodySmall.copy(
                                    color = colorResource(id = R.color.body)
                                ),
                            )

                            Row(
                                Modifier
                                    .fillMaxWidth(0.85f)
                            ) {
                                Text(
                                    text = stringResource(id = R.string.deliveryMethod) + ": ",
                                    style = MaterialTheme.typography.bodySmall.copy(
                                        fontWeight = FontWeight.Bold
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
                                text = stringResource(
                                    id = R.string.orderItems,
                                    order.ordersDescription
                                ),
                                style = MaterialTheme.typography.bodySmall.copy(
                                    color = colorResource(id = R.color.body)
                                ),
                                maxLines = 2,
                                overflow = TextOverflow.Ellipsis,
                            )



                            Spacer(
                                modifier = Modifier
                                    .padding(vertical = Dimens.MediumPadding)
                                    .height(0.5.dp)
                                    .fillMaxWidth()
                                    .background(MaterialTheme.colorScheme.primary.copy(alpha = 0.5f))
                            )

                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .align(Alignment.CenterHorizontally),
                                horizontalArrangement = Arrangement.Center
                            ) {
                                Text(
                                    text = stringResource(id = R.string.price),
                                    style = MaterialTheme.typography.bodyMedium.copy(
                                        color = colorResource(id = R.color.body),

                                        )
                                )
                                Text(
                                    text = stringResource(
                                        id = R.string.prixValue,
                                        order.ordersAmount
                                    ),
                                    style = MaterialTheme.typography.bodyMedium.copy(
                                        fontWeight = FontWeight.Bold,

                                        )
                                )
                            }
                        }
                    }

                    item {
                        Text(
                            text = stringResource(id = R.string.orderItems, ""),
                            style = MaterialTheme.typography.titleSmall.copy(
                                color = colorResource(id = R.color.body)
                            ),
                            modifier = Modifier.padding(start = MediumPadding)
                        )
                    }
                    items(state.items.size, key = { it }) { index ->
                        CartItemOfOrder(
                            modifier = Modifier.padding(top = Dimens.SmallPadding),
                            cartItem = state.items[index].toCartData(),
                            isArabic = isArabic
                        )
                    }

                }
            }

            CustomCircularProgress(isLoading = state.isLoading)
        }
    }

}
