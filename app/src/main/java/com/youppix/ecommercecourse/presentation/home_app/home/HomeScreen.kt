package com.youppix.ecommercecourse.presentation.home_app.home

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.core.screen.ScreenKey
import cafe.adriel.voyager.core.screen.uniqueScreenKey
import cafe.adriel.voyager.hilt.getNavigatorScreenModel
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.youppix.ecommercecourse.common.Dimens.MediumPadding
import com.youppix.ecommercecourse.common.Dimens.SmallPadding
import com.youppix.ecommercecourse.domain.model.items.FilteringItems
import com.youppix.ecommercecourse.presentation.home_app.components.CustomIconItem
import com.youppix.ecommercecourse.presentation.home_app.components.CustomSearchBar
import com.youppix.ecommercecourse.presentation.home_app.home.components.HomeScreenContent
import com.youppix.ecommercecourse.presentation.home_app.search.SearchScreen

class HomeScreen : Screen {
    override val key: ScreenKey = uniqueScreenKey

    @Stable
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        val viewModel = navigator.getNavigatorScreenModel<HomeViewModel>()
        val state = viewModel.homeState.value

        Scaffold(
            modifier = Modifier.fillMaxSize(),
            topBar = {
                Row(
                    Modifier
                        .fillMaxWidth()
                        .padding(horizontal = MediumPadding, vertical = SmallPadding),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    CustomSearchBar(
                        value = "",
                        modifier = Modifier.weight(1f),
                        isEnabled = false,
                        onBoxCLicked = {
                            navigator.push(
                                SearchScreen(
                                    fromSearching = true,
                                    FilteringItems(
                                        itemsCat = if (state.categorySelected == 1) null else state.categorySelected
                                    )
                                )
                            )
                        }
                    )

                    CustomIconItem(
                        modifier = Modifier.padding(start = SmallPadding),
                        imageVector = Icons.Default.Notifications,
                        hasNotification = true
                    ) {}
                }
            }
        ) { innerPadding ->

            HomeScreenContent(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .animateContentSize(),
                state = state,
                goToSearch = { initialDiscount ->
                    println(
                        "flash sale items : ${
                            FilteringItems(
                                itemsCat = state.categorySelected,
                                initialDiscount = initialDiscount
                            )
                        }"
                    )
                    navigator.push(
                        SearchScreen(
                            fromSearching = false,
                            FilteringItems(
                                itemsCat = if (state.categorySelected == 1) null else state.categorySelected,
                                initialDiscount = initialDiscount
                            )
                        )
                    )

                },
                event = viewModel::onEvent
            )

        }

    }
}
