package com.youppix.ecommercecourse.presentation.admin_home_app.items

import androidx.compose.animation.animateContentSize
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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.hilt.getNavigatorScreenModel
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.youppix.ecommercecourse.R
import com.youppix.ecommercecourse.common.Dimens
import com.youppix.ecommercecourse.common.Dimens.MediumPadding
import com.youppix.ecommercecourse.common.Dimens.SmallPadding
import com.youppix.ecommercecourse.domain.model.items.Item
import com.youppix.ecommercecourse.presentation.components.CategoriesItemShimmerEffect
import com.youppix.ecommercecourse.presentation.components.keyboardAsState
import com.youppix.ecommercecourse.presentation.user_home_app.components.CustomIconItem
import com.youppix.ecommercecourse.presentation.user_home_app.components.CustomSearchBar
import com.youppix.ecommercecourse.presentation.user_home_app.components.ItemsList
import com.youppix.ecommercecourse.presentation.admin_home_app.details.DetailsScreen
import com.youppix.ecommercecourse.presentation.user_home_app.search.SearchEvent
import com.youppix.ecommercecourse.presentation.user_home_app.search.components.CategoriesListFlowRow
import com.youppix.ecommercecourse.presentation.user_home_app.search.components.FilteringBottomSheet
import java.util.Locale

class ItemsScreen : Screen {
    @OptIn(ExperimentalFoundationApi::class)
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        val viewModel: ItemsScreenViewModel = navigator.getNavigatorScreenModel()
        val state = viewModel.state.value
        val searchQuery by viewModel.searchQuery.collectAsState()
        val isArabic = Locale.getDefault().language == "ar"
        val lazyListState = rememberLazyListState()

        val focusRequester = remember { FocusRequester() }
        val keyboardController = LocalSoftwareKeyboardController.current

        val focusManager = LocalFocusManager.current
        val isKeyboardOpen by keyboardAsState()

        LaunchedEffect(isKeyboardOpen) {
            if (!isKeyboardOpen) {
                focusManager.clearFocus()
            }
        }

        var showBottomSheet by remember {
            mutableStateOf(false)
        }

        LaunchedEffect(Unit) {
//            if (fromSearching) {
//                focusRequester.requestFocus()
//                keyboardController?.show()
//            }
//            filteringItems?.let {
//                viewModel.onEvent(SearchEvent.UpdateFilteringItems(it))
//                filteringItems = null
//            }
            if (state.categories.isEmpty()) {
                viewModel.onEvent(SearchEvent.GetAllCategories)
            }


        }

        Box(
            modifier = Modifier
                .fillMaxSize()
                .animateContentSize()
        ) {

            Scaffold(modifier = Modifier.fillMaxSize(),
                topBar = {
                    Row(
                        Modifier
                            .fillMaxWidth()
                            .padding(horizontal = MediumPadding, vertical = SmallPadding),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        CustomIconItem(
                            modifier = Modifier.padding(end = SmallPadding),
                            imageVector = Icons.Default.Add,
                        ) {
                            navigator.push(DetailsScreen("0", Item()))
                        }
                        CustomSearchBar(value = searchQuery,
                            modifier = Modifier
                                .weight(1f)
                                .focusRequester(focusRequester),
                            onTextCleared = {
                                viewModel.onEvent(SearchEvent.UpdateSearchQuery(""))
                            },
                            onSearchClicked = {
                                viewModel.onEvent(
                                    SearchEvent.UpdateFilteringItems(
                                        state.filteringItems.copy(
                                            itemsName = searchQuery
                                        )
                                    )
                                )
                            },
                            onTextChange = {
                                viewModel.onEvent(SearchEvent.UpdateSearchQuery(it))
                            })

                        CustomIconItem(
                            modifier = Modifier.padding(start = SmallPadding),
                            painter = painterResource(id = R.drawable.ic_filter),
                        ) {
                            showBottomSheet = true
                        }
                    }
                }) { innerPadding ->


                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(innerPadding)
                        .animateContentSize(),
                    state = lazyListState
                ) {


                    stickyHeader {
                        if (state.categoriesLoading) {
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
                                items(4) {
                                    CategoriesItemShimmerEffect()
                                }
                            }
                        } else {

                            CategoriesListFlowRow(list = state.categories,
                                currentCategory = state.filteringItems.itemsCat,
                                isArabic = isArabic,
                                searchEvent = {
                                    viewModel.onEvent(it)
                                })
                        }
                    }

                    item {
                        ItemsList(
                            state = state,
                            event = viewModel::onEvent,
                            goToDetails = { itemSelected ->
                                navigator.push(
                                    DetailsScreen(
                                        "0",
                                        itemSelected,
                                        colors = state.allColors,
                                        categories = state.categories,
                                        newItem = true
                                    )
                                )
                            })
                    }
                }

            }
        }
        if (showBottomSheet) {
            FilteringBottomSheet(
                allColors = state.allColors,
                filteringItems = state.filteringItems,
                event = viewModel::onEvent
            ) {
                showBottomSheet = false
            }
        }
    }
}