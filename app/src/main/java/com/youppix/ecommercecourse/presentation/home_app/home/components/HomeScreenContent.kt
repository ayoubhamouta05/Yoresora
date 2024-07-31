package com.youppix.ecommercecourse.presentation.home_app.home.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.youppix.ecommercecourse.R
import com.youppix.ecommercecourse.common.Dimens
import com.youppix.ecommercecourse.common.Dimens.MediumPadding
import com.youppix.ecommercecourse.common.Dimens.SearchBarHeight
import com.youppix.ecommercecourse.common.Dimens.SmallPadding
import com.youppix.ecommercecourse.presentation.components.CategoriesItemShimmerEffect
import com.youppix.ecommercecourse.presentation.components.FlashSaleItemShimmerEffect
import com.youppix.ecommercecourse.presentation.components.ItemsListItemShimmerEffect
import com.youppix.ecommercecourse.presentation.components.ShimmerEffect
import com.youppix.ecommercecourse.presentation.home_app.components.CategoriesListItem
import com.youppix.ecommercecourse.presentation.home_app.components.EmptyScreen
import com.youppix.ecommercecourse.presentation.home_app.components.ItemsListItem
import com.youppix.ecommercecourse.presentation.home_app.home.HomeEvent
import com.youppix.ecommercecourse.presentation.home_app.home.HomeState
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import java.util.Locale

@OptIn(ExperimentalLayoutApi::class, ExperimentalFoundationApi::class)
@Stable
@Composable
fun HomeScreenContent(
    modifier: Modifier = Modifier,
    state: HomeState,
    event: (HomeEvent) -> Unit
) {

    val screenSize: Dp = LocalConfiguration.current.screenWidthDp.dp
    val isArabic = Locale.getDefault().language == "ar"
    val lazyListState = rememberLazyListState()
    var showButton by remember { mutableStateOf(false) }
    var showItems by remember { mutableStateOf(false) }

    LaunchedEffect(lazyListState.canScrollForward, state.getItemsError) {
        snapshotFlow { lazyListState.canScrollForward }.collectLatest {
            showButton =
                !lazyListState.canScrollForward && state.items.size >= 6 && state.getItemsError == null
        }
    }
    val scope = rememberCoroutineScope()

    LaunchedEffect(Unit) {
        scope.launch {
            delay(500)
            showItems = true
        }
    }

    if (showItems) {
        Box(modifier = Modifier.fillMaxSize()) {
            LazyColumn(
                modifier = modifier,
                state = lazyListState,
            ) {
                item {
                    Text(
                        text = stringResource(id = R.string.findYourStyle),
                        style = MaterialTheme.typography.displayMedium.copy(
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.primary
                        ),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(
                                horizontal = Dimens.MediumPadding,
                                vertical = Dimens.ExtraSmallPadding
                            ),
                        textAlign = TextAlign.Center
                    )
                }

                item {
                    // Flash Sale Part
                    SectionTitle(
                        title = stringResource(id = R.string.flashSalle),
                        onSeeAllClick = { /* Navigate to categories screen */ }
                    )
                    if (state.isLoading) {
                        FlashSaleItemShimmerEffect()
                    } else {
                        CustomHorizontalPagerFlashSale(
                            items = state.flashSaleItems,
                        ) { itemSelected ->
                            // Handle item selected
                        }
                    }
                }

                item {
                    // New Arrivals Part
                    SectionTitle(
                        title = stringResource(id = R.string.newArrivals),
                        onSeeAllClick = { /* Navigate to categories screen */ }
                    )

                    if (state.isLoading) {
                        FlashSaleItemShimmerEffect()
                    } else {
                        CustomHorizontalPagerNewArrivals(
                            items = state.newArrivals
                        ) { itemSelected ->
                            // Handle item selected
                        }
                    }
                }

                stickyHeader {
                    // Categories Part (Sticky Header)
                    SectionTitle(
                        title = stringResource(id = R.string.categories),
                        onSeeAllClick = { /* Navigate to categories screen */ }
                    )

                    LazyRow(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(MaterialTheme.colorScheme.background)
                            .padding(
                                start = Dimens.SmallPadding.plus(Dimens.ExtraSmallPadding2),
                                bottom = Dimens.SmallPadding,
                            ),
                        contentPadding = PaddingValues(horizontal = Dimens.ExtraSmallPadding),
                    ) {
                        if (state.isLoading) {
                            items(4) {
                                CategoriesItemShimmerEffect()
                            }
                        } else {
                            items(state.categories.size,
                                key = { state.categories[it].name }
                            ) { index ->
                                val currentCategory = state.categories[index]

                                CategoriesListItem(
                                    name = if (isArabic) currentCategory.nameAr else currentCategory.name,
                                    selected = state.categorySelected == currentCategory.id,
                                    id = currentCategory.id
                                ) {
                                    event(HomeEvent.UpdateCategorySelected(state.categories[it].id))
//                                viewModel.updateCategorySelected(
//                                    state.categories[it].id
//                                )
                                }
                            }
                        }

                    }
                }


                item {
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
//                                    viewModel.getItemsByCategory(state.categorySelected)
                                else
                                    event(HomeEvent.GetAllItems)
//                                    viewModel.getAllItems()
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
//                                                .plus(if(showButton) BottomBarHeight else 0.dp)
                                ),
                            horizontalArrangement = Arrangement.spacedBy(Dimens.MediumPadding),
                            maxItemsInEachRow = 3
                        ) {
                            if (state.isLoading) {
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
            }
            ShowAllItemsButton(showButton = showButton)
        }

    } else {
        ShimmerEffect(
            modifier = Modifier
                .fillMaxSize()
                .padding(
                    top = SearchBarHeight.plus(
                        MediumPadding
                    )
                ).clip(RoundedCornerShape(SmallPadding))
                .background(MaterialTheme.colorScheme.background)
        )
    }

}

@Composable
fun SectionTitle(title: String, onSeeAllClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.background)
            .padding(
                start = Dimens.MediumPadding,
                end = Dimens.MediumPadding,
                bottom = Dimens.SmallPadding
            ),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.titleSmall,
            textAlign = TextAlign.Start
        )
        TextButton(onClick = onSeeAllClick) {
            Text(
                text = stringResource(id = R.string.seeAll),
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}