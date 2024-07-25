package com.youppix.ecommercecourse.presentation.home_app.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.youppix.ecommercecourse.common.Dimens.HorizontalPagerCardHeight
import com.youppix.ecommercecourse.common.Dimens.HorizontalPagerContentPadding
import com.youppix.ecommercecourse.common.Dimens.SmallPadding
import kotlinx.coroutines.delay
import kotlin.math.absoluteValue

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CustomHorizontalPager(images: List<Int>,
                          modifier: Modifier = Modifier) {
    val pagerState = rememberPagerState(
        pageCount = { images.size },
        initialPage = 0
    )

    HorizontalPager(
        state = pagerState,
        modifier = modifier
            .height(HorizontalPagerCardHeight),
        contentPadding = PaddingValues(horizontal = HorizontalPagerContentPadding),
    ) { page ->
        val actualPage = page % images.size
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .background(color = Color.Transparent, shape = RoundedCornerShape(SmallPadding))
                .graphicsLayer {
                    val pageOffset = (
                            (pagerState.currentPage - page) + pagerState.currentPageOffsetFraction
                            ).absoluteValue

                    // Apply animations based on pageOffset
                    alpha = lerp(0.5f, 1f, 1f - pageOffset.coerceIn(0f, 1f))
                    scaleX = lerp(0.85f, 1f, 1f - pageOffset.coerceIn(0f, 1f))
                    scaleY = lerp(0.85f, 1f, 1f - pageOffset.coerceIn(0f, 1f))


                    // Add slant effect for the next page with correct alignment
                    val rotationXValue =
                        lerp(8f, 1f, 1f - pageOffset.coerceIn(0f, 1f))


                    when (page) {
                        pagerState.currentPage + 1 -> {
                            rotationX = rotationXValue
                            translationY = lerp(
                                -20.dp.toPx(),
                                1f,
                                1f - pageOffset.coerceIn(0f, 1f)
                            )
                        }

                        pagerState.currentPage - 1 -> {
                            rotationX = rotationXValue
                            translationY = lerp(
                                -20.dp.toPx(),
                                1f,
                                1f - pageOffset.coerceIn(0f, 1f)
                            )
                        }

                        else -> {
                            rotationX = 0f
                            translationY = 0f
                        }
                    }
                }
                .clip(RoundedCornerShape(12.dp)),
            elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)

        ) {
            Image(
                painter = painterResource(id = images[actualPage]),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )
        }
    }
    var toNext by remember {
        mutableStateOf(true)
    }

    LaunchedEffect(pagerState) {


        while (true) {
            delay(2000)
            if (pagerState.currentPage == pagerState.pageCount-1){
                toNext = false
            }
            if (pagerState.currentPage == 0 ){
                toNext = true
            }
            if (toNext){
                pagerState.animateScrollToPage(pagerState.currentPage + 1)
            }else{
                pagerState.animateScrollToPage(pagerState.currentPage - 1)
            }

        }
    }


}

// Custom lerp function
private fun lerp(start: Float, stop: Float, fraction: Float): Float {
    return start + fraction * (stop - start)
}