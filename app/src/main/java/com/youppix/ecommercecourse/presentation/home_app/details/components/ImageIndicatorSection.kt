package com.youppix.ecommercecourse.presentation.home_app.details.components

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.CachePolicy
import coil.request.ImageRequest
import com.youppix.ecommercecourse.common.Dimens
import com.youppix.ecommercecourse.common.Urls
import com.youppix.ecommercecourse.presentation.home_app.details.DetailsState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Stable
@Composable
fun ImageIndicatorSection(
    state: DetailsState,
    pagerState: PagerState
) {

    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    var showAllItemsInPager by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
    ) {
        Card(
            modifier = Modifier
                .wrapContentSize()
                .padding(bottom = Dimens.SmallPadding)
                .padding(horizontal = Dimens.MediumPadding * 2)
                .clip(RoundedCornerShape(Dimens.SmallPadding))
                .align(Alignment.Center)
                .animateContentSize(),
            colors = CardDefaults.cardColors(
                containerColor = Color.White
            )
        ) {
            if (state.details.images.isNotEmpty()) {
                LazyRow(
                    modifier = Modifier
                        .padding(vertical = Dimens.ExtraSmallPadding)
                        .padding(start = Dimens.ExtraSmallPadding)
                        .wrapContentWidth()
                        .clip(RoundedCornerShape(Dimens.SmallPadding))
                        .height(50.dp),
                    horizontalArrangement = Arrangement.Center
                ) {
                    items(if (!showAllItemsInPager && state.details.images.size > 5) 5 else state.details.images.size,
                        key = { state.details.images[it] }) { index ->
                        val imageRequest = ImageRequest.Builder(context)
                            .data(Urls.IMAGES_URL + state.details.images[index])
                            .dispatcher(Dispatchers.IO).diskCachePolicy(CachePolicy.ENABLED)
                            .memoryCachePolicy(CachePolicy.ENABLED).build()

                        Box(
                            contentAlignment = Alignment.Center,
                            modifier = Modifier
                                .padding(end = Dimens.ExtraSmallPadding)
                                .wrapContentSize()
                        ) {
                            AsyncImage(
                                model = imageRequest,
                                contentDescription = null,
                                contentScale = ContentScale.FillWidth,
                                modifier = Modifier
                                    .size(50.dp)
                                    .clip(RoundedCornerShape(Dimens.SmallPadding))
                                    .clickable {
                                        scope.launch {
                                            pagerState.animateScrollToPage(index)
                                        }
                                    },
                                colorFilter = if (index == pagerState.currentPage) ColorFilter.tint(
                                    Color.Gray, BlendMode.Modulate
                                )
                                else null
                            )

                            if (index == 4 && !showAllItemsInPager) {
                                Box(
                                    modifier = Modifier
                                        .size(50.dp)
                                        .background(
                                            Color.Gray.copy(alpha = 0.5f),
                                            shape = RoundedCornerShape(Dimens.SmallPadding)
                                        )
                                        .clickable {
                                            showAllItemsInPager = true
                                        }, contentAlignment = Alignment.Center
                                ) {
                                    Text(
                                        modifier = Modifier.align(Alignment.Center),
                                        text = "+ ${state.details.images.size - 5}",
                                        style = MaterialTheme.typography.bodyLarge.copy(
                                            fontWeight = FontWeight.Bold,
                                            color = MaterialTheme.colorScheme.background
                                        ),
                                        textAlign = TextAlign.Center

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