package com.youppix.ecommercecourse.presentation.home_app.home

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.material3.CircularProgressIndicator
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
import com.youppix.ecommercecourse.presentation.home_app.components.CategoriesListItem
import com.youppix.ecommercecourse.presentation.home_app.components.CustomHorizontalPager
import com.youppix.ecommercecourse.presentation.home_app.components.CustomIconItem
import com.youppix.ecommercecourse.presentation.home_app.components.CustomSearchBar
import com.youppix.ecommercecourse.presentation.home_app.components.ItemsListItem
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

        LaunchedEffect(viewModel.selectedCategory) {

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
                            .padding(horizontal = MediumPadding),
                        textAlign = TextAlign.Start
                    )
                }

                item {
                    // Flash Sale Part
                    SectionTitle(
                        title = stringResource(id = R.string.flashSalle),
                        onSeeAllClick = { /* Navigate to categories screen */ }
                    )

                    CustomHorizontalPager(
                        items = state.flashSaleItems,
                    ) { itemSelected ->
                        // Handle item selected
                    }
                }

                item {
                    // New Arrivals Part
                    SectionTitle(
                        title = stringResource(id = R.string.newArrivals),
                        onSeeAllClick = { /* Navigate to categories screen */ }
                    )

                    CustomHorizontalPager(
                        items = state.newArrivals
                    ) { itemSelected ->
                        // Handle item selected
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
                        items(state.categories.size,
                            key = { state.categories[it].name }
                        ) { index ->
                            val currentCategory = state.categories[index]

                            CategoriesListItem(
                                name = if (isArabic) currentCategory.nameAr else currentCategory.name,
                                selected = viewModel.selectedCategory.value == currentCategory.name ||
                                        viewModel.selectedCategory.value == currentCategory.nameAr
                            ) {
                                viewModel.updateCategorySelected(
                                    it,
                                    index + 1
                                )// todo : handle this from backend
                            }
                        }
                    }
                }

                item {
                    FlowRow(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(
                                start = MediumPadding,
                                end = MediumPadding,
                                top = SmallPadding,
                                bottom = BottomBarHeight
                                    .plus(MediumPadding)
                                    .plus(SmallPadding)
                            ),
                        horizontalArrangement = Arrangement.spacedBy(MediumPadding),
                        maxItemsInEachRow = 3
                    ) {
                        state.items.forEach { item ->
                            ItemsListItem(
                                item = item,
                                modifier = Modifier
                                    .width(screenSize / 3)
                                    .weight(1f)
                            )
                        }
                    }
                }

                if (state.isLoading) {
                    item {
                        CircularProgressIndicator(modifier = Modifier)
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
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}
