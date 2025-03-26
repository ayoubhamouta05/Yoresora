package com.youppix.ecommercecourse.presentation.admin_home_app.orders

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.hilt.getNavigatorScreenModel
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.youppix.ecommercecourse.R
import com.youppix.ecommercecourse.common.Dimens.BottomBarHeight
import com.youppix.ecommercecourse.common.Dimens.ExtraSmallPadding
import com.youppix.ecommercecourse.common.Dimens.LargePadding
import com.youppix.ecommercecourse.common.Dimens.MediumPadding
import com.youppix.ecommercecourse.common.Dimens.SmallPadding
import com.youppix.ecommercecourse.presentation.components.CustomTopAppBar
import com.youppix.ecommercecourse.presentation.components.OrderListItemShimmerEffect
import com.youppix.ecommercecourse.presentation.user_home_app.components.EmptyScreen
import com.youppix.ecommercecourse.presentation.user_home_app.home.HomeScreen
import com.youppix.ecommercecourse.presentation.user_home_app.orders.OrdersEvent
import com.youppix.ecommercecourse.presentation.user_home_app.orders.OrdersType
import com.youppix.ecommercecourse.presentation.user_home_app.orders.OrdersViewModel
import com.youppix.ecommercecourse.presentation.user_home_app.orders.components.OrderListItem
import com.youppix.ecommercecourse.presentation.user_home_app.orders.components.OrdersTabItem
import com.youppix.ecommercecourse.presentation.admin_home_app.orderDetails.OrderDetailsScreen
import java.util.Locale

class OrdersScreen: Screen {
    @OptIn(ExperimentalFoundationApi::class)
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        val viewModel: OrdersViewModel = navigator.getNavigatorScreenModel()
        val state by viewModel.state
        val isArabic = Locale.getDefault().language == "ar"

        LaunchedEffect(Unit) {
            viewModel.onEvent(OrdersEvent.GetAllOrders(userId = 0))
        }

        Scaffold(
            modifier = Modifier.fillMaxSize(),
            topBar = {
                CustomTopAppBar(title = stringResource(id = R.string.numberOfOrders),
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
                    .padding(horizontal = SmallPadding)
            ) {
                stickyHeader {
                    Row(
                        Modifier
                            .fillMaxWidth()
                            .background(MaterialTheme.colorScheme.background)
                            .padding(SmallPadding)
                    ) {
                        OrdersTabItem(
                            title = stringResource(id = R.string.completed),
                            selected = state.ordersType == OrdersType.COMPLETED
                        ) {
                            viewModel.onEvent(OrdersEvent.UpdateOrderType(OrdersType.COMPLETED))
                        }
                        OrdersTabItem(
                            title = stringResource(id = R.string.pending),
                            selected = state.ordersType == OrdersType.PAID
                        ) {
                            viewModel.onEvent(OrdersEvent.UpdateOrderType(OrdersType.PAID))
                        }
                        OrdersTabItem(
                            title = stringResource(id = R.string.unfinished),
                            selected = state.ordersType == OrdersType.UNFINISHED
                        ) {
                            viewModel.onEvent(OrdersEvent.UpdateOrderType(OrdersType.UNFINISHED))
                        }
                    }
                }

                if (state.isLoading){
                    items(3){
                        OrderListItemShimmerEffect()
                    }
                }else if(state.ordersList.isEmpty()){
                    item {
                        Box(modifier = Modifier.fillMaxSize().padding(vertical = MediumPadding *2)) {
                            if (state.error.isNullOrEmpty()) {
                                EmptyScreen(
                                    emptyMessage = when (state.ordersType) {
                                        OrdersType.PAID -> {
                                            stringResource(
                                                id = R.string.youDontHaveAnyOrders, stringResource(
                                                    id = R.string.pending
                                                )
                                            )
                                        }

                                        OrdersType.UNFINISHED -> {
                                            stringResource(
                                                id = R.string.youDontHaveAnyOrders, stringResource(
                                                    id = R.string.unfinished
                                                )
                                            )
                                        }

                                        OrdersType.COMPLETED -> {
                                            stringResource(
                                                id = R.string.youDontHaveAnyOrders, stringResource(
                                                    id = R.string.completed
                                                )
                                            )
                                        }
                                    }
                                )
                            }else{
                                EmptyScreen(error = state.error) {
                                    viewModel.onEvent(OrdersEvent.GetAllOrders(userId = 0))
                                }
                            }
                        }
                    }
                }else {
                    items(state.ordersList.size, key = { state.ordersList[it].ordersId }) { index ->
                        OrderListItem(
                            modifier = Modifier
                                .padding(horizontal = SmallPadding, vertical = ExtraSmallPadding)
                                .padding(
                                    bottom = if (index == state.ordersList.lastIndex) BottomBarHeight.plus(
                                        LargePadding
                                    ) else 0.dp
                                ),
                            order = state.ordersList[index]
                        ) {
                            navigator.push(OrderDetailsScreen(order = state.ordersList[index]))
                        }
                    }
                }
            }


        }

    }
}