package com.youppix.ecommercecourse.presentation.home_app.cart

import android.util.Log
import androidx.compose.foundation.background
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
import com.youppix.ecommercecourse.common.Dimens.LargePadding
import com.youppix.ecommercecourse.common.Dimens.SmallPadding
import com.youppix.ecommercecourse.domain.model.cart.toItem
import com.youppix.ecommercecourse.domain.model.items.Item
import com.youppix.ecommercecourse.presentation.components.CustomTopAppBar
import com.youppix.ecommercecourse.presentation.home_app.cart.components.BottomSection
import com.youppix.ecommercecourse.presentation.home_app.cart.components.CartItem
import com.youppix.ecommercecourse.presentation.home_app.checkout.CheckoutScreen
import com.youppix.ecommercecourse.presentation.home_app.details.DetailsEvent
import com.youppix.ecommercecourse.presentation.home_app.details.DetailsScreen
import com.youppix.ecommercecourse.presentation.home_app.home.HomeScreen
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
                    navigator.push(CheckoutScreen())
                }
            }
        ) { innerPadding ->

            LazyColumn(
                Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
            ) {
                items(state.cartItems.size) { index ->

                    Column {
                        CartItem(
                            cartItem = state.cartItems[index], isArabic = isArabic
                        ) {
                            navigator.push(
                                DetailsScreen(
                                    newItem = true,
                                    item = it.toItem(),
                                    userId = null
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

    }
}