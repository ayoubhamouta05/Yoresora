package com.youppix.ecommercecourse.presentation.home_app.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.youppix.ecommercecourse.common.Dimens
import com.youppix.ecommercecourse.presentation.components.ItemsListItemShimmerEffect
import com.youppix.ecommercecourse.presentation.home_app.home.HomeEvent
import com.youppix.ecommercecourse.presentation.home_app.home.HomeState
import com.youppix.ecommercecourse.presentation.home_app.search.SearchEvent
import com.youppix.ecommercecourse.presentation.home_app.search.SearchState

@OptIn(ExperimentalLayoutApi::class)
@Stable
@Composable
fun ItemsList(
    state: HomeState,
    event: (HomeEvent) -> Unit
) {
    val screenSize: Dp = LocalConfiguration.current.screenWidthDp.dp
    if (state.getItemsError != null) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(
                    start = Dimens.MediumPadding,
                    end = Dimens.MediumPadding,
                    top = Dimens.SmallPadding,
                    bottom = Dimens.BottomBarHeight
                        .plus(Dimens.SmallPadding)
                )
        ) {
            EmptyScreen(state.getItemsError) {
                if (state.categorySelected > 1)
                    event(HomeEvent.GetItemsByCategory(state.categorySelected))
                else
                    event(HomeEvent.GetAllItems)
            }
        }

    } else {
        FlowRow(
            modifier = Modifier
                .fillMaxSize()
                .padding(
                    start = Dimens.MediumPadding,
                    end = Dimens.MediumPadding,
                    top = Dimens.SmallPadding,
                    bottom = Dimens.BottomBarHeight
                        .plus(Dimens.SmallPadding)
                ),
            horizontalArrangement = Arrangement.spacedBy(Dimens.MediumPadding),
            maxItemsInEachRow = 3
        ) {
            if (state.isItemsCategoriesLoading) {
                repeat(2) {
                    ItemsListItemShimmerEffect(
                        Modifier
                            .width(screenSize / 2.5f)
                            .weight(1f)
                    )
                }
            } else {
                state.items.forEach { item ->
                    ItemsListItem(
                        item = item,
                        modifier = Modifier
                            .width(screenSize / 2.5f)
                            .weight(1f)
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Stable
@Composable
fun ItemsList(
    state: SearchState,
    event: (SearchEvent) -> Unit
) {
    val screenSize: Dp = LocalConfiguration.current.screenWidthDp.dp
    if (state.getItemsError != null) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(
                    start = Dimens.MediumPadding,
                    end = Dimens.MediumPadding,
                    top = Dimens.SmallPadding,
                    bottom = Dimens.BottomBarHeight
                        .plus(Dimens.SmallPadding)
                ),
            contentAlignment = Alignment.Center
        ) {
            EmptyScreen(state.getItemsError) {
                event(SearchEvent.GetItemsByFiltering(state.filteringItems))
            }
        }

    } else {
        FlowRow(
            modifier = Modifier
                .fillMaxSize()
                .padding(
                    start = Dimens.MediumPadding,
                    end = Dimens.MediumPadding,
                    top = Dimens.SmallPadding,
                    bottom = Dimens.BottomBarHeight
                        .plus(Dimens.SmallPadding)
                ),
            horizontalArrangement = Arrangement.spacedBy(Dimens.MediumPadding),
            maxItemsInEachRow = 3
        ) {
            if (state.itemsLoading) {
                repeat(4) {
                    ItemsListItemShimmerEffect(
                        Modifier
                            .width(screenSize / 2.5f)
                            .weight(1f)
                    )
                }
            } else {
                state.items.forEach { item ->
                    ItemsListItem(
                        item = item,
                        modifier = Modifier
                            .width(screenSize / 2.5f)
                            .weight(1f)
                    )
                }
            }
        }
    }
}