package com.youppix.ecommercecourse.presentation.home_app.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.core.screen.ScreenKey
import cafe.adriel.voyager.core.screen.uniqueScreenKey
import com.youppix.ecommercecourse.common.Dimens.MediumPadding
import com.youppix.ecommercecourse.common.Dimens.SmallPadding
import com.youppix.ecommercecourse.presentation.home_app.components.CustomIconItem
import com.youppix.ecommercecourse.presentation.home_app.components.CustomSearchBar
import com.youppix.ecommercecourse.presentation.home_app.components.EmptyScreen
import com.youppix.ecommercecourse.presentation.home_app.home.components.HomeScreenContent

class HomeScreen : Screen {
    override val key: ScreenKey = uniqueScreenKey

    @Composable
    override fun Content() {
        val viewModel: HomeViewModel = hiltViewModel()
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
                        value = state.searchQuery,
                        modifier = Modifier.weight(1f),
                        onTextCleared = {
                            viewModel.onEvent(HomeEvent.UpdateSearchQuery(""))
                        },
                        onSearchClicked = {},
                        onTextChange = {
                            viewModel.onEvent(HomeEvent.UpdateSearchQuery(""))
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

            if (state.getHomeDataError != null) {
                Box(modifier = Modifier.fillMaxSize()) {
                    EmptyScreen(state.getHomeDataError) {

                        viewModel.onEvent(HomeEvent.GetHomeData)
                        viewModel.onEvent(
                            HomeEvent.UpdateCategorySelected(state.categorySelected) // to refresh the items either
                        )

                    }
                }
            } else {
                HomeScreenContent(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(innerPadding),
                    state = state,
                    event = viewModel::onEvent
                )
            }

        }

    }
}
