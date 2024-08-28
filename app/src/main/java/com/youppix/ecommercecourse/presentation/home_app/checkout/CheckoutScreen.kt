package com.youppix.ecommercecourse.presentation.home_app.checkout

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ShoppingBag
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.hilt.getNavigatorScreenModel
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.youppix.ecommercecourse.R
import com.youppix.ecommercecourse.common.Dimens
import com.youppix.ecommercecourse.common.Dimens.BottomBarHeight
import com.youppix.ecommercecourse.common.Dimens.LargePadding
import com.youppix.ecommercecourse.common.Dimens.MediumPadding
import com.youppix.ecommercecourse.common.Dimens.SmallPadding
import com.youppix.ecommercecourse.domain.model.cart.CartData
import com.youppix.ecommercecourse.presentation.components.CustomTopAppBar
import com.youppix.ecommercecourse.presentation.home_app.checkout.components.DeliveryMethodItem
import com.youppix.ecommercecourse.presentation.home_app.checkout.components.OrderListItem
import com.youppix.ecommercecourse.presentation.home_app.checkout.components.ShippingAddressItem
import com.youppix.ecommercecourse.presentation.home_app.home.HomeScreen
import java.util.Locale

data class CheckoutScreen(
    val orderList: List<CartData> = emptyList(),
) : Screen {
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        val viewModel : CheckoutViewModel = navigator.getNavigatorScreenModel()
        val state by viewModel.state
        val isArabic = Locale.getDefault().language == "ar"

        Scaffold(
            modifier = Modifier.fillMaxSize(),
            topBar = {
                CustomTopAppBar(title = stringResource(id = R.string.checkout), isArabic = isArabic,
                    onBackClicked = {
                        if (navigator.canPop) {
                            navigator.pop()
                        } else {
                            navigator.replace(HomeScreen())
                        }
                    })
            },
            bottomBar = {
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
                    Button(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = Dimens.MediumPadding , horizontal = LargePadding),
                        onClick = {
//                            navigator.push(PaymentMethodsScreen())
                        }) {
                        Text(
                            modifier = Modifier.padding(vertical = Dimens.ExtraSmallPadding2),
                            text = stringResource(id = R.string.continueToPayment),
                            style = MaterialTheme.typography.bodyMedium.copy(
                                fontWeight = FontWeight.Bold
                            )
                        )
                    }
                }

            }
        ) { innerPadding ->
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .padding(start = MediumPadding, end = MediumPadding, top = SmallPadding ,)
            ) {

                item {
                    Text(
                        text = stringResource(id = R.string.ShippingAddress),
                        style = MaterialTheme.typography.titleSmall,
                        textAlign = TextAlign.Start
                    )
                }

                item {
                    ShippingAddressItem(
                        addressTitle = "Home",
                        addressInfo = "Batna, Batna, Parck A Fourage Batna, Batna, Parck A Fourage ",
                        modifier = Modifier.padding(vertical = SmallPadding),
                    ) {
//                        navigator.push(AddAddressScreen())
                    }
                }

                item {
                    Spacer(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = SmallPadding, vertical = SmallPadding)
                            .height(0.3.dp)
                            .background(color = MaterialTheme.colorScheme.primary.copy(alpha = 0.3f))
                    )
                }

                item {
                    Text(
                        text = stringResource(id = R.string.deliveryMethod),
                        style = MaterialTheme.typography.titleSmall,
                        textAlign = TextAlign.Start
                    )
                }

                item {
                    DeliveryMethodItem(
                        methodTitle = stringResource(id = R.string.homeDelivery),
                        methodInfo = stringResource(id = R.string.homeDeliveryDescription),
                        modifier = Modifier.padding(vertical = SmallPadding),
                        isSelected = state.isHomeDelivery
                    ) {
                        viewModel.onEvent(CheckoutEvent.UpdateDeliveryMethod(true))
                    }
                }
                item {
                    DeliveryMethodItem(
                        methodTitle = stringResource(id = R.string.pickupPointDelivery),
                        methodInfo = stringResource(id = R.string.pickupPointDeliveryDescription),
                        modifier = Modifier.padding(vertical = SmallPadding),
                        isSelected = !state.isHomeDelivery
                    ) {
                        viewModel.onEvent(CheckoutEvent.UpdateDeliveryMethod(false))
                    }
                }

                item {
                    Text(
                        text = stringResource(id = R.string.orderList),
                        style = MaterialTheme.typography.titleSmall,
                        textAlign = TextAlign.Start
                    )
                }

                items(orderList.size, key = { it }) { index ->
                    OrderListItem(
                        modifier = Modifier.padding(vertical = SmallPadding),
                        cartItem = orderList[index],
                        isArabic = isArabic
                    )
                }
            }

        }
    }
}