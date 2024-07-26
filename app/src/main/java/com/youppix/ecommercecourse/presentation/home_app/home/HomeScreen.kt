package com.youppix.ecommercecourse.presentation.home_app.home

import android.app.Activity
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import cafe.adriel.voyager.core.screen.Screen
import com.youppix.ecommercecourse.R
import com.youppix.ecommercecourse.common.Dimens.BottomBarHeight
import com.youppix.ecommercecourse.common.Dimens.ExtraSmallPadding
import com.youppix.ecommercecourse.common.Dimens.ExtraSmallPadding2
import com.youppix.ecommercecourse.common.Dimens.HorizontalPaddingSignIn
import com.youppix.ecommercecourse.common.Dimens.MediumPadding
import com.youppix.ecommercecourse.common.Dimens.SmallPadding
import com.youppix.ecommercecourse.domain.model.items.Item
import com.youppix.ecommercecourse.presentation.home_app.components.CategoriesListItem
import com.youppix.ecommercecourse.presentation.home_app.components.CustomHorizontalPager
import com.youppix.ecommercecourse.presentation.home_app.components.CustomIconItem
import com.youppix.ecommercecourse.presentation.home_app.components.CustomSearchBar
import com.youppix.ecommercecourse.presentation.home_app.components.ItemsList
import com.youppix.ecommercecourse.presentation.home_app.components.ItemsListItem
import com.youppix.ecommercecourse.presentation.starting_app.onBoarding.components.PageIndicator
import kotlinx.coroutines.launch
import java.util.Locale


class HomeScreen() : Screen {


    @OptIn(ExperimentalLayoutApi::class)
    @Composable
    override fun Content() {
        val viewModel: HomeViewModel = hiltViewModel()
        val state = viewModel.homeState.value
        val isArabic = Locale.getDefault().language == "ar"
        val scope = rememberCoroutineScope()
        LaunchedEffect(Unit) {
            scope.launch {
                viewModel.getHomeData()
            }
        }
        Scaffold(
            modifier = Modifier.fillMaxSize(),


            ) { innerPadding ->
            LazyColumn(
                Modifier
                    .fillMaxSize()
                    .padding(innerPadding),
                contentPadding = PaddingValues(vertical = SmallPadding)
            ) {
                item {
                    Row(
                        Modifier
                            .fillMaxWidth()
                            .padding(horizontal = MediumPadding),
                        horizontalArrangement = Arrangement.SpaceBetween, // Change to SpaceBetween to space out elements
                        verticalAlignment = Alignment.CenterVertically,

                        ) {
                        CustomSearchBar(value = state.searchQuery,
                            modifier = Modifier.weight(1f),
                            onTextCleared = {
                                viewModel.updateSearchQuery("")
                            },
                            onSearchClicked = {},
                            onTextChange = {
                                viewModel.updateSearchQuery(it)
                            }

                        )

                        CustomIconItem(
                            modifier = Modifier.padding(start = SmallPadding),
                            imageVector = Icons.Default.Notifications
                        ) {}
                    }
                }

                item {
                    Text(
                        text = stringResource(id = R.string.findYourStyle),
                        style = MaterialTheme.typography.displayMedium.copy(fontWeight = FontWeight.Bold),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(
                                horizontal = MediumPadding, vertical = SmallPadding
                            ),
                        textAlign = TextAlign.Start
                    )
                }

                item {
                    val items = arrayListOf<Item>()
                    if (state.items.isNotEmpty()) for (i in 0 until 5) items.add(state.items[0])
                    CustomHorizontalPager(
                        items = items,
                    ) { itemSelected ->
                        // todo : on item selected
                    }
                }

                item {
                    Text(
                        text = "New Arrivals",
                        style = MaterialTheme.typography.titleSmall,
                        textAlign = TextAlign.Start,
                        modifier = Modifier.padding(start = MediumPadding, top = SmallPadding)
                    )
                }

                item {
                    Row(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(
                                start = MediumPadding, end = MediumPadding, bottom = SmallPadding
                            ),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Categories",
                            style = MaterialTheme.typography.titleSmall,
                            textAlign = TextAlign.Start
                        )

                        TextButton(
                            onClick = {
                                // todo : go to categories screen
                            },
                        ) {
                            Text(
                                text = "See All", style = MaterialTheme.typography.bodyMedium
                            )
                        }
                    }
                }

                item {
                    LazyRow(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(start = MediumPadding, bottom = SmallPadding),
                        contentPadding = PaddingValues(horizontal = ExtraSmallPadding),
                    ) {
                        items(state.categories.size) { index ->
                            val currentCategory = state.categories[index]

                            CategoriesListItem(
                                name = if (isArabic) currentCategory.nameAr else currentCategory.name,
                                selected = state.categorySelected == currentCategory.name || state.categorySelected == currentCategory.nameAr
                            ) {
                                viewModel.updateCategorySelected(it)
                            }

                        }
                    }
                }


                items(10) { i ->
                    val itemSize: Dp = (LocalConfiguration.current.screenWidthDp.dp / 2) //grid size
                    Row(Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center) {

                            if (state.items.isNotEmpty()) {
                                ItemsListItem(
                                    item = state.items[0], modifier = Modifier.width(itemSize)
                                )
                            }


                            if (state.items.isNotEmpty()) {
                                ItemsListItem(
                                    item = state.items[0], modifier = Modifier.width(itemSize)
                                )
                            }


                    }


                }
            }

        }
    }
}
