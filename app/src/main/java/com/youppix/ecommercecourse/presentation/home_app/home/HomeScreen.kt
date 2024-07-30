package com.youppix.ecommercecourse.presentation.home_app.home

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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import cafe.adriel.voyager.core.screen.Screen
import com.youppix.ecommercecourse.R
import com.youppix.ecommercecourse.common.Dimens.BottomBarHeight
import com.youppix.ecommercecourse.common.Dimens.ExtraSmallPadding
import com.youppix.ecommercecourse.common.Dimens.ExtraSmallPadding2
import com.youppix.ecommercecourse.common.Dimens.MediumPadding
import com.youppix.ecommercecourse.common.Dimens.SmallPadding
import com.youppix.ecommercecourse.presentation.components.CategoriesItemShimmerEffect
import com.youppix.ecommercecourse.presentation.components.ItemsListItemShimmerEffect
import com.youppix.ecommercecourse.presentation.components.MostPopularItemShimmerEffect
import com.youppix.ecommercecourse.presentation.home_app.components.CategoriesListItem
import com.youppix.ecommercecourse.presentation.home_app.home.components.CustomHorizontalPagerFlashSale
import com.youppix.ecommercecourse.presentation.home_app.components.CustomIconItem
import com.youppix.ecommercecourse.presentation.home_app.components.CustomSearchBar
import com.youppix.ecommercecourse.presentation.home_app.components.EmptyScreen
import com.youppix.ecommercecourse.presentation.home_app.components.ItemsListItem
import com.youppix.ecommercecourse.presentation.home_app.home.components.CustomHorizontalPagerNewArrivals
import kotlinx.coroutines.launch
import java.util.Locale

class HomeScreen : Screen {

    @OptIn(ExperimentalLayoutApi::class, ExperimentalFoundationApi::class)
    @Composable
    override fun Content() {
        val viewModel: HomeViewModel = hiltViewModel()
        val state = viewModel.homeState.value
        val isArabic = Locale.getDefault().language == "ar"
        val scope = rememberCoroutineScope()
        val lazyListState = rememberLazyListState()
        val screenSize: Dp = LocalConfiguration.current.screenWidthDp.dp

        LaunchedEffect(Unit) {
            scope.launch {
                viewModel.getHomeData()
                viewModel.getAllItems()
            }
        }

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
                        onTextCleared = { viewModel.updateSearchQuery("") },
                        onSearchClicked = {},
                        onTextChange = { viewModel.updateSearchQuery(it) }
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
                        scope.launch {
                            viewModel.getHomeData()
                            viewModel.updateCategorySelected(state.categorySelected) // to refresh the items either
                        }
                    }
                }
            } else {
                LazyColumn(
                    state = lazyListState,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(innerPadding)
                ) {
                    item {
                        Text(
                            text = stringResource(id = R.string.findYourStyle),
                            style = MaterialTheme.typography.displayMedium.copy(fontWeight = FontWeight.Bold),
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = MediumPadding, vertical = SmallPadding),
                            textAlign = TextAlign.Start
                        )
                    }

                    item {
                        // Flash Sale Part
                        SectionTitle(
                            title = stringResource(id = R.string.flashSalle),
                            onSeeAllClick = { /* Navigate to categories screen */ }
                        )
                        if (state.isLoading) {
                            MostPopularItemShimmerEffect()
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
                            MostPopularItemShimmerEffect()
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
                                    start = SmallPadding.plus(ExtraSmallPadding2),
                                    bottom = SmallPadding,
                                ),
                            contentPadding = PaddingValues(horizontal = ExtraSmallPadding),
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
                                        viewModel.updateCategorySelected(
                                            state.categories[it].id
                                        )
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
                                        start = MediumPadding,
                                        end = MediumPadding,
                                        top = SmallPadding,
                                        bottom = BottomBarHeight
                                            .plus(SmallPadding)
                                    )
                            ) {
                                EmptyScreen(state.getItemsError) {
                                    scope.launch {
                                        if (state.categorySelected > 1)
                                            viewModel.getItemsByCategory(state.categorySelected)
                                        else
                                            viewModel.getAllItems()
                                    }
                                }
                            }

                        } else {
                            FlowRow(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .padding(
                                        start = MediumPadding,
                                        end = MediumPadding,
                                        top = SmallPadding,
                                        bottom = BottomBarHeight
                                            .plus(SmallPadding)
                                    ),
                                horizontalArrangement = Arrangement.spacedBy(MediumPadding),
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
            .padding(start = MediumPadding, end = MediumPadding, bottom = SmallPadding),
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
                style = MaterialTheme.typography.bodyMedium.copy(
                    fontWeight = FontWeight.Normal
                )
            )
        }
    }
}
