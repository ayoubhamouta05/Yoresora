package com.youppix.ecommercecourse.presentation.user_home_app.cart

import android.util.Log
import android.widget.Toast
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.hilt.getNavigatorScreenModel
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.youppix.ecommercecourse.R
import com.youppix.ecommercecourse.common.Constant
import com.youppix.ecommercecourse.common.Dimens.BottomBarHeight
import com.youppix.ecommercecourse.common.Dimens.LargePadding
import com.youppix.ecommercecourse.common.Dimens.MediumPadding
import com.youppix.ecommercecourse.common.Dimens.SmallPadding
import com.youppix.ecommercecourse.domain.model.cart.toItem
import com.youppix.ecommercecourse.presentation.components.CustomCircularProgress
import com.youppix.ecommercecourse.presentation.components.CustomTopAppBar
import com.youppix.ecommercecourse.presentation.user_home_app.cart.components.BottomSection
import com.youppix.ecommercecourse.presentation.user_home_app.cart.components.CartItem
import com.youppix.ecommercecourse.presentation.user_home_app.checkout.CheckoutScreen
import com.youppix.ecommercecourse.presentation.user_home_app.components.EmptyScreen
import com.youppix.ecommercecourse.presentation.user_home_app.details.DetailsScreen
import com.youppix.ecommercecourse.presentation.user_home_app.home.HomeScreen
import java.util.Locale


data class CartScreen(private val userId: String?) : Screen {

    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        val viewModel: CartViewModel = navigator.getNavigatorScreenModel()
        val state by viewModel.state
        val isArabic = Locale.getDefault().language == "ar"
        val context = LocalContext.current

        LaunchedEffect(Unit) {
            userId?.let { id ->
                viewModel.setUserId(id.toInt())
                viewModel.onEvent(CartEvent.GetCartItems(userId = id.toInt()))
            } ?: run {
                val id = context.getSharedPreferences(Constant.APP_ENTRY, 0).getString("userId", "")
                if (!id.isNullOrEmpty()) {
                    viewModel.setUserId(id.toInt())
                    viewModel.onEvent(CartEvent.GetCartItems(userId = id.toInt()))
                }
            }
        }

        Scaffold(
            modifier = Modifier.fillMaxSize(),
            topBar = {
                CustomTopAppBar(title = stringResource(id = R.string.myCart), isArabic = isArabic,
                    onBackClicked = {
                        if (navigator.canPop) {
                            Log.d("CartScreen", "can pop : " + navigator.canPop.toString())
                            navigator.pop()
                        } else {
                            navigator.replace(HomeScreen())
                        }
                    })
            },
            bottomBar = {
                BottomSection(state = state, event = viewModel::onEvent) {
                    if (state.cartItems.isNotEmpty()) {
                        navigator.push(
                            CheckoutScreen(
                                userId = userId!!.toInt(),
                                orderList = state.cartItems ,
                                totalPrice = state.totalCost
                            )
                        )
                    } else {
                        Toast.makeText(
                            context,
                            R.string.youMustHaveAtLeastOneItem,
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            }
        ) { innerPadding ->

            LazyColumn(
                Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .animateContentSize()
            ) {

                if (state.cartItems.isEmpty() && !state.isLoading) {
                    item {
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(
                                    start = MediumPadding,
                                    end = MediumPadding,
                                    top = LargePadding * 2,
                                    bottom = BottomBarHeight
                                        .plus(SmallPadding)
                                ),
                            contentAlignment = Alignment.Center
                        ) {
                            EmptyScreen(
                                emptyMessage = stringResource(id = R.string.emptyCartMessage)
                            )
                        }
                    }
                } else {
                    items(state.cartItems.size, key = { it }) { index ->

                        Column {
                            CartItem(
                                cartItem = state.cartItems[index],
                                isArabic = isArabic,
                                index = index,
                                event = viewModel::onEvent
                            ) {
                                navigator.push(
                                    DetailsScreen(
                                        newItem = true,
                                        item = it.toItem(),
                                        userId = null,
                                        initialColor = it.item_color,
                                        initialSize = it.item_size
                                    )
                                )
                            }

                            Spacer(
                                modifier = Modifier
                                    .padding(vertical = SmallPadding, horizontal = LargePadding)
                                    .fillMaxWidth()
                                    .height(if (index != state.cartItems.size - 1) 0.5.dp else 0.dp)
                                    .background(
                                        color = MaterialTheme.colorScheme.primary.copy(alpha = 0.5f)
                                    )
                            )
                        }


                    }
                }
            }

            CustomCircularProgress(
                isLoading = state.isLoading,
                modifier = Modifier.padding(bottom = LargePadding * 4)
            )


        }

    }
}