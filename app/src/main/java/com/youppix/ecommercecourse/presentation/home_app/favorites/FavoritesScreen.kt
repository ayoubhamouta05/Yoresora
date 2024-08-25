package com.youppix.ecommercecourse.presentation.home_app.favorites

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.MaterialTheme
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
import com.youppix.ecommercecourse.common.Dimens
import com.youppix.ecommercecourse.common.Dimens.SmallPadding
import com.youppix.ecommercecourse.presentation.components.CategoriesItemShimmerEffect
import com.youppix.ecommercecourse.presentation.components.CustomTopAppBar
import com.youppix.ecommercecourse.presentation.home_app.components.CategoriesListItem
import com.youppix.ecommercecourse.presentation.home_app.components.ItemsList
import com.youppix.ecommercecourse.presentation.home_app.details.DetailsScreen
import com.youppix.ecommercecourse.presentation.home_app.home.HomeScreen
import java.util.Locale


class FavoritesScreen(val userId: String?) : Screen {

    @OptIn(ExperimentalFoundationApi::class)
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        val viewModel: FavoritesViewModel = navigator.getNavigatorScreenModel()
        val isArabic = Locale.getDefault().language == "ar"
        val context = LocalContext.current

        val state by viewModel.state

        LaunchedEffect(Unit) {
            if (state.categories.isEmpty()){
                viewModel.onEvent(FavoriteEvent.GetAllCategories)
            }
            userId?.let {
                viewModel.setUserId(userId.toInt())
                viewModel.onEvent(
                    FavoriteEvent.UpdateCategorySelected(
                        userId.toInt(),
                        state.categorySelected
                    )
                )
            }?: run {
                val id = context.getSharedPreferences(Constant.APP_ENTRY, 0).getString("userId", "")
                if (!id.isNullOrEmpty()) {
                    viewModel.setUserId(id.toInt())
                    viewModel.onEvent(
                        FavoriteEvent.UpdateCategorySelected(
                            id.toInt(),
                            state.categorySelected
                        )
                    )
                }
            }
        }

        Scaffold(modifier = Modifier.fillMaxSize(),
            topBar = {
                CustomTopAppBar(
                    title = stringResource(id = R.string.wishList),
                    isArabic = isArabic
                ) {
                    if (navigator.canPop) {
                        navigator.pop()
                    } else {
                        navigator.replace(HomeScreen())
                    }
                }
            }) { innerPadding ->
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding),
            ) {

                stickyHeader {
                    LazyRow(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(MaterialTheme.colorScheme.background)
                            .padding(
                                start = SmallPadding.plus(Dimens.ExtraSmallPadding2),
                                bottom = SmallPadding,
                            ),
                        contentPadding = PaddingValues(horizontal = Dimens.ExtraSmallPadding),
                    ) {
                        if (state.categoriesLoading) {
                            items(4) {
                                CategoriesItemShimmerEffect()
                            }
                        } else {
                            items(state.categories.size,
                                key = { it }
                            ) { index ->
                                val currentCategory = state.categories[index]

                                CategoriesListItem(
                                    name = if (isArabic) currentCategory.nameAr else currentCategory.name,
                                    selected = state.categorySelected == index,
                                    id = index
                                ) {
                                    viewModel.onEvent(
                                        FavoriteEvent.UpdateCategorySelected(
                                            userId = userId!!.toInt(),
                                            it
                                        )
                                    )
                                }
                            }
                        }

                    }
                }


                item {
                    ItemsList(
                        state = state,
                        event = viewModel::onEvent,
                        goToDetails = { itemSelected ->
                            navigator.push(DetailsScreen(userId, itemSelected, true))
                        })
                }

            }
        }
    }
}