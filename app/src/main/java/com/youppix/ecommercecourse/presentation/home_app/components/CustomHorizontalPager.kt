package com.youppix.ecommercecourse.presentation.home_app.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import com.youppix.ecommercecourse.common.Dimens.HorizontalPagerCardHeight
import com.youppix.ecommercecourse.common.Dimens.MediumPadding
import com.youppix.ecommercecourse.common.Dimens.SmallPadding
import com.youppix.ecommercecourse.common.Urls.IMAGES_URL
import com.youppix.ecommercecourse.domain.model.items.Item
import com.youppix.ecommercecourse.presentation.starting_app.onBoarding.components.PageIndicator
import kotlinx.coroutines.delay

@OptIn(ExperimentalFoundationApi::class)
@Stable
@Composable
fun CustomHorizontalPager(
    items: List<Item>,
    modifier: Modifier = Modifier,
    onDetailsClick: (Item) -> Unit
) {
    val pagerState = rememberPagerState(
        pageCount = { items.size },
        initialPage = 0
    )

    Column(modifier = modifier) {
        HorizontalPager(
            state = pagerState,
            modifier
                .height(HorizontalPagerCardHeight)
                .padding(bottom = MediumPadding)
        ) { page ->
            val actualPage = page % items.size
            MostPopularItem(
                imageUrl = IMAGES_URL + items[actualPage].itemImage,
                itemName = items[actualPage].itemName,
                itemDiscount = items[actualPage].itemDiscount.toString(),
                itemPrice = items[actualPage].itemPrice.toString(),
                time = items[actualPage].itemPrice.toString()
            ) {
                onDetailsClick(items[actualPage])
            }
        }

        PageIndicator(
            pageSize = items.size, selectedPage = pagerState.currentPage,
            modifier = Modifier.align(CenterHorizontally),
            indicatorSize = SmallPadding
        )
    }


    // handle infinite loop
//    var toNext by remember {
//        mutableStateOf(true)
//    }
//
//    LaunchedEffect(pagerState) {
//
//
//        while (true) {
//            delay(2500)
//            if (pagerState.currentPage == pagerState.pageCount - 1) {
//                toNext = false
//            }
//            if (pagerState.currentPage == 0) {
//                toNext = true
//            }
//            if (toNext) {
//                pagerState.animateScrollToPage(pagerState.currentPage + 1)
//            } else {
//                pagerState.animateScrollToPage(pagerState.currentPage - 1)
//            }
//
//        }
//    }


}