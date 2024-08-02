package com.youppix.ecommercecourse.presentation.home_app.home.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import com.youppix.ecommercecourse.R
import com.youppix.ecommercecourse.common.Dimens
import com.youppix.ecommercecourse.common.Dimens.MediumPadding
import com.youppix.ecommercecourse.common.Dimens.SearchBarHeight
import com.youppix.ecommercecourse.common.Dimens.SmallPadding
import com.youppix.ecommercecourse.presentation.components.CategoriesItemShimmerEffect
import com.youppix.ecommercecourse.presentation.components.FlashSaleItemShimmerEffect
import com.youppix.ecommercecourse.presentation.home_app.components.CategoriesListItem
import com.youppix.ecommercecourse.presentation.home_app.components.EmptyScreen
import com.youppix.ecommercecourse.presentation.home_app.components.ItemsList
import com.youppix.ecommercecourse.presentation.home_app.home.HomeEvent
import com.youppix.ecommercecourse.presentation.home_app.home.HomeState
import kotlinx.coroutines.flow.collectLatest
import java.util.Locale

@OptIn(ExperimentalFoundationApi::class)
@Stable
@Composable
fun HomeScreenContent(
    modifier: Modifier = Modifier,
    state: HomeState,
    goToSearch: (initialDiscount: Int) -> Unit,
    event: (HomeEvent) -> Unit
) {

    val isArabic = Locale.getDefault().language == "ar"
    val lazyListState = rememberLazyListState()
    var showButton by remember { mutableStateOf(false) }

    LaunchedEffect(lazyListState.canScrollForward, state.getItemsError, state.items) {
        snapshotFlow { lazyListState.canScrollForward }.collectLatest {
            showButton =
                !lazyListState.canScrollForward && state.items.size >= 6 && state.getItemsError == null
        }
    }
    if (state.getHomeDataError != null) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = SearchBarHeight.plus(MediumPadding))
        ) {
            EmptyScreen(state.getHomeDataError) {

                event(HomeEvent.GetHomeData)
                event(
                    HomeEvent.UpdateCategorySelected(state.categorySelected) // to refresh the items either
                )

            }
        }
    } else {
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
                        onSeeAllClick = { goToSearch(1) }
                    )
                    if (state.isHomeLoading) {
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
                        onSeeAllClick = { goToSearch(0) }
                    )

                    if (state.isHomeLoading) {
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
                        onSeeAllClick = { goToSearch(0) },
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
                        if (state.isHomeLoading) {
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
                                }
                            }
                        }

                    }
                }


                item {
                    ItemsList(state = state, event)
                }
            }
            ShowAllItemsButton(showButton = showButton) {
                goToSearch(0)
            }
        }
    }


}

@Composable
fun SectionTitle(title: String, onSeeAllClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.background)
            .padding(
                start = MediumPadding,
                end = MediumPadding,
                bottom = SmallPadding
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