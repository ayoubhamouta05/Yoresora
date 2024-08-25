package com.youppix.ecommercecourse.presentation.home_app.details

import android.util.Log
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.hilt.getNavigatorScreenModel
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.youppix.ecommercecourse.R
import com.youppix.ecommercecourse.common.Constant
import com.youppix.ecommercecourse.domain.model.details.toSize
import com.youppix.ecommercecourse.domain.model.items.Item
import com.youppix.ecommercecourse.presentation.components.CustomDialog
import com.youppix.ecommercecourse.presentation.home_app.details.components.BottomBarSection
import com.youppix.ecommercecourse.presentation.home_app.details.components.DetailsScreenContent
import com.youppix.ecommercecourse.presentation.home_app.customSize.CustomSizeScreen

data class DetailsScreen(
    private val userId: String?,
    private val item: Item,
    private var newItem: Boolean? = null,
    private var initialColor: Int? = null,
    private val initialSize: Int? = null,
) : Screen {
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        val viewModel: DetailsViewModel = navigator.getNavigatorScreenModel()
        val state by viewModel.state
        val context = LocalContext.current

        LaunchedEffect(Unit) {
            userId?.let { id ->
                viewModel.setUserId(id.toInt())
                newItem?.let {
                    viewModel.onEvent(DetailsEvent.GetItemDetails(item.itemId, id.toInt()))
                    newItem = null
                }
            } ?: run {
                val id = context.getSharedPreferences(Constant.APP_ENTRY, 0).getString("userId", "")
                if (!id.isNullOrEmpty()) {
                    viewModel.setUserId(id.toInt())
                    newItem?.let {
                        viewModel.onEvent(DetailsEvent.GetItemDetails(item.itemId, id.toInt()))
                        newItem = null
                    }
                }
            }
            viewModel.onEvent(DetailsEvent.SetInitialColorAndSize(initialColor, initialSize, state))
        }

        LaunchedEffect(state.goToCustomSizeScreen) {
            if (state.goToCustomSizeScreen) {
                state.userId?.let {
                    navigator.push(
                        CustomSizeScreen(
                            it,
                            state.details.sizes[state.selectedSize].toSize()
                        )
                    )
                    newItem = true // to fetch data when pop
                    viewModel.onEvent(DetailsEvent.HideDialog)
                }

            }
        }


        LaunchedEffect(state.selectedSize, state.selectedColor, state.details.initialData) {

            if (state.details.initialData != null) {
                Log.d("DetailsScreen", "initial data : ${state.details.initialData!!}")
                Log.d("DetailsScreen", "${state.selectedSize} : sizes : ${state.details.sizes}")
                Log.d("DetailsScreen", "${state.selectedColor} :colors : ${state.details.colors}")
                for (initialData in state.details.initialData!!) {
                    if (initialData.itemSize == state.details.sizes[state.selectedSize].sizes_id &&
                        initialData.itemColor == state.details.colors[state.selectedColor].colors_id
                    ) {
//                        Log.d("DetailsScreen", "initial data : ${state.details.initialData!!}")
                        // remove from cart
                        viewModel.onEvent(DetailsEvent.ToggleAddToCart(false))
                        break
                    } else {
                        // add to cart
                        viewModel.onEvent(DetailsEvent.ToggleAddToCart(true))
                    }
                }
//                Log.d("DetailsScreen", "initial data : ${state.details.initialData!!}")
//
//                Log.d("DetailsScreen", "size : ${state.details.sizes[state.selectedSize].sizes_id} , color : ${state.details.colors[state.selectedColor].colors_id}")
            } else {
                // add to cart : item not exist
                viewModel.onEvent(DetailsEvent.ToggleAddToCart(true))
            }

        }



        Scaffold(modifier = Modifier
            .fillMaxSize()
            .animateContentSize(),
            bottomBar = {
                BottomBarSection(item.itemPrice.toString(), state.addToCartState) {
                    viewModel.onEvent(
                        DetailsEvent.AddOrDeleteCartItem(
                            itemId = item.itemId,
                            userId = state.userId!!,
                            itemSize = state.details.sizes[state.selectedSize].sizes_id,
                            itemColor = state.details.colors[state.selectedColor].colors_id
                        )
                    )
                }
            }

        ) { innerPadding ->


            DetailsScreenContent(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .animateContentSize(),
                event = viewModel::onEvent,
                state = state,
                item = item,
                userId = state.userId ?: 0,
                makeCustomSize = {
                    state.userId?.let {
                        viewModel.onEvent(DetailsEvent.CheckSizeExistence(it))
                    }
                },
                onBackClicked = { navigator.pop() }
            )

            CustomDialog(
                title = stringResource(id = R.string.alreadyHaveCustomSize),
                message = stringResource(id = R.string.alreadyHaveCustomSizeMessage),
                showDialog = state.sizeAlreadyExistDialog,
                onConfirmRequest = {
                    state.userId?.let {
                        navigator.push(
                            CustomSizeScreen(
                                it,
                                state.details.sizes[state.details.sizes.size - 1].toSize().copy()
                            )
                        )
                        newItem = true
                    }
                    viewModel.onEvent(DetailsEvent.HideDialog)
                },
                onDismissRequest = {
                    viewModel.onEvent(DetailsEvent.HideDialog)
                }
            )

        }
    }
}
