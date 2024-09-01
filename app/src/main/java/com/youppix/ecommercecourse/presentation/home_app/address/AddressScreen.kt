package com.youppix.ecommercecourse.presentation.home_app.address

import android.util.Log
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.hilt.getNavigatorScreenModel
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.youppix.ecommercecourse.R
import com.youppix.ecommercecourse.common.Dimens.LargePadding
import com.youppix.ecommercecourse.common.Dimens.SmallPadding
import com.youppix.ecommercecourse.presentation.components.CustomTopAppBar
import com.youppix.ecommercecourse.presentation.components.keyboardAsState
import com.youppix.ecommercecourse.presentation.home_app.address.components.AddressBottomSheet
import com.youppix.ecommercecourse.presentation.home_app.checkout.components.ShippingAddressItem
import com.youppix.ecommercecourse.presentation.home_app.home.HomeScreen
import com.youppix.ecommercecourse.presentation.starting_app.auth.components.CustomProgressIndicator
import java.util.Locale

data class AddressScreen(
    private val userId: Int,
    private val fromCheckout: Boolean = false,
) : Screen {
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        val viewModel: AddressViewModel = navigator.getNavigatorScreenModel()
        val state by viewModel.state

        LaunchedEffect(Unit) {
            viewModel.onEvent(AddressEvent.GetAllAddress(userId))
        }

        val isArabic = Locale.getDefault().language == "ar"
        Scaffold(
            modifier = Modifier
                .fillMaxSize(),
            topBar = {
                CustomTopAppBar(
                    title = stringResource(id = R.string.address),
                    isArabic = isArabic
                ) {
                    if (navigator.canPop) {
                        navigator.pop()
                    } else {
                        navigator.replace(HomeScreen())
                    }
                }
            },
            floatingActionButton = {
                FloatingActionButton(
                    modifier = Modifier.padding(SmallPadding),
                    onClick = {
                        viewModel.onEvent(AddressEvent.ToggleSelectedAddressId(address = null))
                        viewModel.onEvent(AddressEvent.ToggleShowBottomSheet(isInserting = true))
                    }, shape = CircleShape,
                    contentColor = MaterialTheme.colorScheme.background,
                    containerColor = MaterialTheme.colorScheme.primary,
                    elevation = FloatingActionButtonDefaults.elevation(
                        defaultElevation = 4.dp
                    )
                ) {
                    Icon(imageVector = Icons.Default.Add, contentDescription = null)
                }
            }
        ) { innerPadding ->
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .animateContentSize()
            ) {
                items(state.items.size) { index ->

                        ShippingAddressItem(
                            modifier = Modifier
                                .padding(vertical = SmallPadding)
                                .padding(horizontal = SmallPadding)
                                .background(
                                    if (state.items[index].addressDefault == 1) {
                                        viewModel.onEvent(AddressEvent.UpdateDefaultAddressIndex(index)) // get the default address index
                                        MaterialTheme.colorScheme.primary.copy(alpha = 0.1f)
                                    } else Color.Transparent,
                                    shape = RoundedCornerShape(SmallPadding)
                                )
                                ,
                            isArabic = isArabic,
                            address = state.items[index],
                            onSelectClick = {
                                if (state.items[index].addressDefault != 1) { // don't update values when he select the same address
                                    viewModel.onEvent(
                                        AddressEvent.UpsertAddress(
                                            state.items[state.defaultAddressIndex].copy(
                                                addressDefault = 0
                                            )
                                        )
                                    )
                                    viewModel.onEvent(
                                        AddressEvent.UpsertAddress(
                                            state.items[index].copy(
                                                addressDefault = 1
                                            )
                                        )
                                    )
                                }
                                if (fromCheckout)
                                    navigator.pop()
                            },
                            onUpdateClick = {
                                viewModel.onEvent(AddressEvent.ToggleShowBottomSheet(isInserting = false))
                                viewModel.onEvent(AddressEvent.ToggleSelectedAddressId(state.items[index]))
                            }
                        )

                }
            }

            if (state.showBottomSheet) {
                AddressBottomSheet(
                    modifier = Modifier.padding(bottom = LargePadding),
                    isInserting = state.isInserting,
                    userId = userId,
                    state = state,
                    isArabic = isArabic,
                    event = viewModel::onEvent
                ) {
                    viewModel.onEvent(AddressEvent.ToggleShowBottomSheet(isInserting = null))
                }
            }

            CustomProgressIndicator(show = state.isLoading)

        }

    }
}