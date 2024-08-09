package com.youppix.ecommercecourse.presentation.home_app.favorites

import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.hilt.getNavigatorScreenModel
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.youppix.ecommercecourse.R
import com.youppix.ecommercecourse.common.Dimens
import com.youppix.ecommercecourse.common.Dimens.SmallPadding
import com.youppix.ecommercecourse.presentation.components.CategoriesItemShimmerEffect
import com.youppix.ecommercecourse.presentation.home_app.components.CategoriesListItem
import com.youppix.ecommercecourse.presentation.home_app.components.CustomIcon
import com.youppix.ecommercecourse.presentation.home_app.components.ItemsList
import com.youppix.ecommercecourse.presentation.home_app.details.DetailsScreen
import com.youppix.ecommercecourse.presentation.home_app.home.HomeScreen
import java.util.Locale


class FavoritesScreen(val userId: String?) : Screen {

    @OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        val viewModel: FavoritesViewModel = navigator.getNavigatorScreenModel()
        val isArabic = Locale.getDefault().language == "ar"

        val state by viewModel.state

        LaunchedEffect(Unit) {
            userId?.let {
                viewModel.setUserId(userId.toInt())
                viewModel.onEvent(FavoriteEvent.GetAllFavorites(userId.toInt(), 0))
            }
        }

        LaunchedEffect(state.items , state.itemsLoading) {
            Log.d("FavoritesScreen", "Loading = ${state.itemsLoading} , items = ${state.items.size}")
        }

        Scaffold(modifier = Modifier.fillMaxSize(),
            topBar = {
                CenterAlignedTopAppBar(
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = MaterialTheme.colorScheme.background
                    ),
                    title = {
                        Text(
                            text = stringResource(id = R.string.wishList),
                            textAlign = TextAlign.Center,
                            style = MaterialTheme.typography.titleSmall
                        )
                    },
                    modifier = Modifier
                        .background(MaterialTheme.colorScheme.background)
                        .padding(horizontal = SmallPadding)
                        .padding(top = SmallPadding),
                    navigationIcon = {
                        CustomIcon(
                            modifier = Modifier.rotate(
                                if (isArabic) 180f else 0f
                            ),
                            imageVector = Icons.Default.ArrowBack
                        ) {
                            if (navigator.canPop) {
                                navigator.pop()
                            } else {
                                navigator.replace(HomeScreen())
                            }
                        }
                    }
                )
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
                                key = { state.categories[it].name }
                            ) { index ->
                                val currentCategory = state.categories[index]

                                CategoriesListItem(
                                    name = if (isArabic) currentCategory.nameAr else currentCategory.name,
                                    selected = state.categorySelected == if (currentCategory.id > 1) currentCategory.id else 0,
                                    id = currentCategory.id
                                ) {
                                    viewModel.onEvent(
                                        FavoriteEvent.UpdateCategorySelected(
                                            userId = userId!!.toInt(),
                                            state.categories[it].id
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
                            navigator.push(DetailsScreen(userId ,itemSelected, true))
                        })
                }

            }
        }
    }
}