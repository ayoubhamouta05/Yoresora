package com.youppix.ecommercecourse.presentation.home_app.orders

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.hilt.getNavigatorScreenModel
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.youppix.ecommercecourse.R
import com.youppix.ecommercecourse.common.Dimens.SmallPadding
import com.youppix.ecommercecourse.presentation.components.CustomTopAppBar
import com.youppix.ecommercecourse.presentation.home_app.home.HomeScreen
import com.youppix.ecommercecourse.presentation.home_app.orders.components.OrdersTabItem
import java.util.Locale


class OrdersScreen() : Screen {

    @OptIn(ExperimentalFoundationApi::class)
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        val viewModel: OrdersViewModel = navigator.getNavigatorScreenModel()
        val state by viewModel.state
        val isArabic = Locale.getDefault().language == "ar"
        val context = LocalContext.current


        Scaffold(
            modifier = Modifier.fillMaxSize(),
            topBar = {
                CustomTopAppBar(title = stringResource(id = R.string.myOrders),
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
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
            ) {
                stickyHeader {
                    Row(
                        Modifier
                            .fillMaxWidth()
                            .padding(SmallPadding)
                    ) {
                        OrdersTabItem(
                            title = stringResource(id = R.string.completed),
                            selected = state.ordersType == OrdersType.COMPLETED_ORDERS
                        ) {
                            viewModel.onEvent(OrdersEvent.UpdateOrderType(OrdersType.COMPLETED_ORDERS))
                        }
                        OrdersTabItem(
                            title = stringResource(id = R.string.pending),
                            selected = state.ordersType == OrdersType.PENDING_DELIVERY
                        ) {
                            viewModel.onEvent(OrdersEvent.UpdateOrderType(OrdersType.PENDING_DELIVERY))
                        }
                        OrdersTabItem(
                            title = stringResource(id = R.string.unfinished),
                            selected = state.ordersType == OrdersType.UNFINISHED_ORDERS
                        ) {
                            viewModel.onEvent(OrdersEvent.UpdateOrderType(OrdersType.UNFINISHED_ORDERS))
                        }
                    }
                }


            }

        }

    }


}