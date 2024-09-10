package com.youppix.ecommercecourse.presentation.home_app.ordersDetails

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.hilt.getNavigatorScreenModel
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.youppix.ecommercecourse.R
import com.youppix.ecommercecourse.common.Dimens
import com.youppix.ecommercecourse.domain.model.orders.toCartData
import com.youppix.ecommercecourse.presentation.components.CustomCircularProgress
import com.youppix.ecommercecourse.presentation.components.CustomTopAppBar
import com.youppix.ecommercecourse.presentation.home_app.checkout.components.CartItemOfOrder
import com.youppix.ecommercecourse.presentation.home_app.home.HomeScreen
import java.util.Locale

data class OrderDetailsScreen(val orderId: String) : Screen {
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        val viewModel: OrderDetailsViewModel = navigator.getNavigatorScreenModel()
        val state by viewModel.state
        val isArabic = Locale.getDefault().language == "ar"

        LaunchedEffect(Unit) {
            viewModel.onEvent(OrderDetailsEvent.GetOrderDetails(orderId))
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
            if (!state.isLoading){
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(innerPadding)
                        .padding(horizontal = Dimens.SmallPadding)
                ) {
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
