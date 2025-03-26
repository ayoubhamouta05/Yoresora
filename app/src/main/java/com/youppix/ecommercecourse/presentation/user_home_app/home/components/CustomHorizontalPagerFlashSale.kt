package com.youppix.ecommercecourse.presentation.user_home_app.home.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.youppix.ecommercecourse.common.Dimens.HorizontalPagerCardHeight
import com.youppix.ecommercecourse.common.Dimens.SmallPadding
import com.youppix.ecommercecourse.domain.model.items.Item
import com.youppix.ecommercecourse.presentation.starting_app.onBoarding.components.PageIndicator

@OptIn(ExperimentalFoundationApi::class)
@Stable
@Composable
fun CustomHorizontalPagerFlashSale(
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
                .height(HorizontalPagerCardHeight )
                .padding(bottom = SmallPadding),
            key =  {
                items[it].itemName
            }
        ) { page ->
            val actualPage = page % items.size
            val item = remember(items[actualPage]) { items[actualPage] }
            FlashSaleItem(
                item = item
            ) {
                onDetailsClick(item)
            }

        }

        PageIndicator(
            pageSize = items.size, selectedPage = pagerState.currentPage,
            modifier = Modifier.align(CenterHorizontally),
            indicatorSize = SmallPadding,
            selectedColor = MaterialTheme.colorScheme.primary,
            unselectedColor = if ( isSystemInDarkTheme()) Color.DarkGray else Color.LightGray
        )
    }
}