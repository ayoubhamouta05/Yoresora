package com.youppix.ecommercecourse.presentation.onBoarding

import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import com.youppix.ecommercecourse.R
import com.youppix.ecommercecourse.common.Constant.pages
import com.youppix.ecommercecourse.common.Dimens.PageIndicatorWidth
import com.youppix.ecommercecourse.presentation.components.PageIndicator
import com.youppix.ecommercecourse.presentation.login.LoginScreen
import kotlinx.coroutines.launch


class OnBoardingScreen(private val modifier: Modifier = Modifier) : Screen {
    @OptIn(ExperimentalFoundationApi::class)
    @Composable
    override fun Content() {
        val pagerState = rememberPagerState(initialPage = 0) {
            pages.size
        }

        val navigator = LocalNavigator.current

        Column(
            modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            HorizontalPager(
                state = pagerState,
            ) { index ->
                Column(
                    modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = stringResource(id = pages[index].title),
                        style = MaterialTheme.typography.titleSmall,
                        modifier = Modifier.padding(bottom = 10.dp)
                    )
                    Image(
                        painter = painterResource(id = pages[index].image),
                        contentDescription = null,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(400.dp),
                        contentScale = ContentScale.Fit,

                        )
                    Text(
                        text = stringResource(id = pages[index].body),
                        color = Color.DarkGray,
                        style = MaterialTheme.typography.displaySmall,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.padding(horizontal = 30.dp),
                        lineHeight = TextUnit(35f, TextUnitType.Sp)
                    )
                }

            }
            PageIndicator(
                modifier = Modifier.width(PageIndicatorWidth),
                pageSize = pagerState.pageCount,
                selectedPage = pagerState.currentPage
            )
            Spacer(modifier = Modifier.weight(1F))
            val scope = rememberCoroutineScope()
            Button(modifier = Modifier
                .fillMaxWidth()
                .padding(end = 30.dp, start = 30.dp, bottom = 30.dp)
                .background(
                    color = MaterialTheme.colorScheme.primary,
                    shape = RoundedCornerShape(10.dp)
                ), onClick = {
                scope.launch {
                    pagerState.animateScrollToPage(
                        page = pagerState.currentPage + 1,
                        animationSpec = tween(
                            durationMillis = 800,
                            delayMillis = 100
                        )
                    )
                }
                if (pagerState.currentPage == pagerState.pageCount - 1) {
                    navigator?.push(LoginScreen())
                }
            }) {
                Text(text = stringResource(id = R.string.continuee))
            }
        }
    }
}