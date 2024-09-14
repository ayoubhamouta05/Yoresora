package com.youppix.ecommercecourse.presentation.home_app.checkout

import android.widget.Toast
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.hilt.getNavigatorScreenModel
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.youppix.ecommercecourse.R
import com.youppix.ecommercecourse.common.Constant.APP_ENTRY
import com.youppix.ecommercecourse.common.Dimens.MediumPadding
import com.youppix.ecommercecourse.common.Dimens.SmallPadding
import com.youppix.ecommercecourse.domain.model.cart.CartData
import com.youppix.ecommercecourse.presentation.components.CustomCircularProgress
import com.youppix.ecommercecourse.presentation.components.CustomTopAppBar
import com.youppix.ecommercecourse.presentation.home_app.address.AddressScreen
import com.youppix.ecommercecourse.presentation.home_app.address.components.ShippingAddressItem
import com.youppix.ecommercecourse.presentation.home_app.checkout.components.BottomSection
import com.youppix.ecommercecourse.presentation.home_app.checkout.components.DeliveryMethodItem
import com.youppix.ecommercecourse.presentation.home_app.checkout.components.CartItemOfOrder
import com.youppix.ecommercecourse.presentation.home_app.components.EmptyScreen
import com.youppix.ecommercecourse.presentation.home_app.home.HomeScreen
import com.youppix.ecommercecourse.presentation.home_app.payment.PaymentScreen
import java.util.Locale

data class CheckoutScreen(
    val userId: Int,
    val totalPrice: Float = 0f,
    val orderList: List<CartData> = emptyList(),
) : Screen {
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        val viewModel: CheckoutViewModel = navigator.getNavigatorScreenModel()
        val state by viewModel.state
        val isArabic = Locale.getDefault().language == "ar"
        val context = LocalContext.current

        LaunchedEffect(Unit) {
            viewModel.onEvent(CheckoutEvent.SetSubTotal(value = totalPrice))
            viewModel.onEvent(CheckoutEvent.GetAddress(userId = userId))
            val customerId =
                context.getSharedPreferences(APP_ENTRY, 0).getString("userCustomerId", "") ?: ""
            viewModel.onEvent(CheckoutEvent.UpdateCustomerId(customerId = customerId))
        }

        LaunchedEffect(state.checkoutUrl) {
            state.checkoutUrl?.let {
                navigator.push(PaymentScreen(userId = userId, url = it))
                viewModel.onEvent(CheckoutEvent.ResetCheckoutUrl)
            }
        }

        LaunchedEffect(state.error) {
            if (!state.error.isNullOrEmpty() && !state.isLoading) {
                Toast.makeText(context, state.error, Toast.LENGTH_SHORT).show()
            }
        }

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

                BottomSection(
                    state = state
                ) {
                    if (state.address != null) {
                        viewModel.onEvent(
                            CheckoutEvent.CreateCheckoutUrl(
                                local = Locale.getDefault().language,
                                description = "",
                                amount = state.totalPrice,
                                customerId = state.customerId!!,
                                userId = userId,
                                carts = orderList,
                                deliveryMethod = if (state.isHomeDelivery)  1 else 2,
                                shippingAddress = state.address!!
                            )
                        )
                    } else {
                        Toast.makeText(
                            context,
                            context.getString(R.string.emptyAddressMessage).substringBefore(","),
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }

            }
        ) { innerPadding ->
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .padding(start = MediumPadding, end = MediumPadding, top = SmallPadding)
                    .animateContentSize()
            ) {

                item {
                    Text(
                        text = stringResource(id = R.string.ShippingAddress),
                        style = MaterialTheme.typography.titleSmall,
                        textAlign = TextAlign.Start
                    )
                }

                item {
                    if (state.address == null) {
                        Box(modifier = Modifier.padding(SmallPadding)) {
                            EmptyScreen(emptyMessage = stringResource(id = R.string.emptyAddressMessage)) {
                                navigator.push(AddressScreen(userId, fromCheckout = true))
                            }
                        }
                    } else {
                        ShippingAddressItem(
                            address = state.address!!,
                            modifier = Modifier.padding(vertical = SmallPadding),
                            isArabic = isArabic
                        ) {
                            navigator.push(
                                AddressScreen(
                                    userId,
                                    fromCheckout = true
                                )
                            )
                        }
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
                    CartItemOfOrder(
                        modifier = Modifier.padding(vertical = SmallPadding),
                        cartItem = orderList[index],
                        isArabic = isArabic
                    )
                }
            }
            CustomCircularProgress(
                isLoading = state.isLoading
            )
        }
    }
}