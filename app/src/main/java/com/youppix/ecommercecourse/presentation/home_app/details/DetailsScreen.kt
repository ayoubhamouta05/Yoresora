package com.youppix.ecommercecourse.presentation.home_app.details

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.hilt.getNavigatorScreenModel
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import coil.compose.AsyncImage
import coil.request.CachePolicy
import coil.request.ImageRequest
import com.youppix.ecommercecourse.R
import com.youppix.ecommercecourse.common.Dimens
import com.youppix.ecommercecourse.common.Dimens.MediumPadding
import com.youppix.ecommercecourse.common.Dimens.SmallPadding
import com.youppix.ecommercecourse.common.Urls
import com.youppix.ecommercecourse.domain.model.items.ColorData
import com.youppix.ecommercecourse.domain.model.items.Item
import com.youppix.ecommercecourse.presentation.home_app.components.CategoriesListItem
import com.youppix.ecommercecourse.presentation.home_app.components.ColorItemWithId
import com.youppix.ecommercecourse.presentation.home_app.details.components.BottomBarSection
import com.youppix.ecommercecourse.presentation.home_app.details.components.ImageIndicatorSection
import kotlinx.coroutines.Dispatchers
import java.util.Locale

data class DetailsScreen(private val item: Item) : Screen {
    @OptIn(ExperimentalFoundationApi::class, ExperimentalLayoutApi::class)
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        val viewModel: DetailsViewModel = navigator.getNavigatorScreenModel()
        val state by viewModel.state
        val context = LocalContext.current

        val isArabic = Locale.getDefault().language == "ar"

        LaunchedEffect(Unit) {
            viewModel.getDetails(item.itemId, item.itemCat)
        }

        val pagerState =
            rememberPagerState(pageCount = { state.details.images.size }, initialPage = 0)

        val brush = Brush.linearGradient(
            listOf(
                MaterialTheme.colorScheme.background,
                MaterialTheme.colorScheme.background,
                MaterialTheme.colorScheme.background,
                MaterialTheme.colorScheme.primary
            ),
            start = Offset(0f, 0f),
            end = Offset(1000f, 1000f)
        )


        Scaffold(modifier = Modifier
            .fillMaxSize()
            .animateContentSize(),
            bottomBar = {
                BottomBarSection(item.itemPrice.toString())
            }

        ) { innerPadding ->
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .animateContentSize()
            ) {
                // HorizontalPager
                item {
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .fillParentMaxHeight(0.4f),
                        colors = CardDefaults.cardColors(
                            containerColor = MaterialTheme.colorScheme.surface
                        ),
                        shape = RoundedCornerShape(bottomStart = 70.dp, bottomEnd = 70.dp)
                    ) {
                        HorizontalPager(state = pagerState,
                            modifier = Modifier
                                .fillParentMaxSize(),
                            key = { state.details.images[it] }) { page ->
                            Box(modifier = Modifier
                                .fillMaxSize()
                                .drawBehind {
                                    drawRect(brush)
                                }) {
                                val currentPage =
                                    if (page == 0) Urls.IMAGES_URL + item.itemImage else Urls.IMAGES_URL + state.details.images[page]
                                val imageRequest = ImageRequest.Builder(context).data(currentPage)
                                    .dispatcher(Dispatchers.IO).diskCachePolicy(CachePolicy.ENABLED)
                                    .memoryCachePolicy(CachePolicy.ENABLED).build()

                                AsyncImage(
                                    model = imageRequest,
                                    contentDescription = null,
                                    contentScale = ContentScale.Fit,
                                    modifier = Modifier
                                        .wrapContentSize()
                                        .align(Alignment.Center)
                                        .clip(RoundedCornerShape(SmallPadding))
                                )
                            }

                        }
                    }
                }
                // Image Indicator
                item {
                    ImageIndicatorSection(state = state, pagerState = pagerState)
                }

                item {
                    Text(
                        text = if (isArabic) state.details.categories_name_ar else state.details.categories_name,
                        style = MaterialTheme.typography.bodyMedium.copy(
                            fontWeight = FontWeight.Normal
                        ),
                        textAlign = TextAlign.Start,
                        modifier = Modifier.padding(horizontal = MediumPadding)
                    )
                }
                item {
                    Text(
                        text = if (isArabic) item.itemNameAr else item.itemName,
                        style = MaterialTheme.typography.displayMedium.copy(
                            fontWeight = FontWeight.SemiBold
                        ),
                        textAlign = TextAlign.Start,
                        modifier = Modifier.padding(horizontal = MediumPadding)
                    )
                }

                item {
                    Text(
                        text = stringResource(id = R.string.details),
                        style = MaterialTheme.typography.titleSmall,
                        textAlign = TextAlign.Start,
                        modifier = Modifier.padding(horizontal = MediumPadding)
                    )
                }

                item {
                    Text(
                        text = if (isArabic) item.itemDescAr else item.itemDesc,
                        style = MaterialTheme.typography.bodyMedium.copy(
                            color = colorResource(id = R.color.text_medium)
                        ),
                        textAlign = TextAlign.Start,
                        modifier = Modifier.padding(horizontal = MediumPadding)
                    )
                }

                item {
                    Spacer(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = MediumPadding, vertical = MediumPadding)
                            .height(1.dp)
                            .background(color = MaterialTheme.colorScheme.primary.copy(alpha = 0.3f))

                    )
                }

                item {
                    Text(
                        text = stringResource(id = R.string.selectSize) + " :",
                        style = MaterialTheme.typography.titleSmall,
                        textAlign = TextAlign.Start,
                        modifier = Modifier.padding(horizontal = MediumPadding)
                    )
                }
                item {
                    LazyRow(modifier = Modifier.padding(horizontal = MediumPadding)) {
                        items(state.details.sizes_name.size) { index ->
                            CategoriesListItem(
                                name = state.details.sizes_name[index],
                                id = state.details.sizes_id[index],
                                selected = state.details.sizes_id[index] == state.selectedSize
                            ) {
                                viewModel.updateSizeSelected(it)
                            }
                        }
                    }

                }
                item {
                    Text(
                        text = stringResource(id = R.string.choseColors) + " :",
                        style = MaterialTheme.typography.titleSmall,
                        textAlign = TextAlign.Start,
                        modifier = Modifier.padding(
                            start = MediumPadding,
                            end = MediumPadding,
                            top = SmallPadding
                        )
                    )
                }
                item {
                    FlowRow(
                        Modifier
                            .fillMaxWidth()
                            .padding(horizontal = MediumPadding)
                            .padding(bottom = MediumPadding),
                        horizontalArrangement = Arrangement.Start,
                    ) {
                        repeat(state.details.colors_name.size) { index ->
                            val color = ColorData(
                                colors_name = state.details.colors_name[index],
                                colors_hex = state.details.colors_hex[index],
                                colors_id = state.details.colors_id[index],
                                colors_name_ar = state.details.colors_name_ar[index]
                            )
                            ColorItemWithId(
                                modifier = Modifier.padding(end = Dimens.ExtraSmallPadding),
                                color = color,
                                selected = state.selectedColors == color.colors_id
                            ) {
                               viewModel.updateColorSelected(color.colors_id)
                            }
                        }
                    }

                }


            }
        }
    }
}
