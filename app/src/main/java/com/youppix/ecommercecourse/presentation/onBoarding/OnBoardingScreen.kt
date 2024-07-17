package com.youppix.ecommercecourse.presentation.onBoarding

import android.content.res.Configuration
import android.util.Log
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.fillMaxHeight
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
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.BeyondBoundsLayout
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import com.youppix.ecommercecourse.R
import com.youppix.ecommercecourse.common.Constant
import com.youppix.ecommercecourse.common.Constant.pages
import com.youppix.ecommercecourse.common.Constant.setLocal
import com.youppix.ecommercecourse.common.Dimens
import com.youppix.ecommercecourse.common.Dimens.HorizontalPaddingSignIn
import com.youppix.ecommercecourse.common.Dimens.PageIndicatorWidth
import com.youppix.ecommercecourse.common.Dimens.SmallPadding
import com.youppix.ecommercecourse.presentation.onBoarding.components.PageIndicator
import com.youppix.ecommercecourse.presentation.auth.login.LoginScreen
import com.youppix.ecommercecourse.presentation.auth.login.LoginState
import kotlinx.coroutines.launch
import java.util.Locale


class OnBoardingScreen() : Screen {
    @OptIn(ExperimentalFoundationApi::class)
    @Composable
    override fun Content() {
        val pagerState = rememberPagerState(initialPage = 0) {
            pages.size
        }
        val navigator = LocalNavigator.current
        val currentLang = LocalContext.current.getSharedPreferences(Constant.APP_LANG, 0)
            .getString(Constant.APP_LANG, Locale.getDefault().language) ?: "en"
        setLocal(currentLang, LocalContext.current)

        Log.d("OnBoardingScreen", "lang: $currentLang , local : ${Locale.getDefault().language}")
        CompositionLocalProvider(
            if (currentLang == "en") {
                LocalLayoutDirection provides LayoutDirection.Ltr
            } else {
                LocalLayoutDirection provides LayoutDirection.Rtl
            }
        ) {
            Scaffold(
                modifier = Modifier.fillMaxSize(),
            ) { innerPadding ->
                Column(
                    modifier = Modifier.padding(
                        top = innerPadding.calculateTopPadding(),
                        end = innerPadding.calculateEndPadding(
                            if (currentLang == "ar")
                                LayoutDirection.Rtl else LayoutDirection.Ltr
                        ),
                        start = innerPadding.calculateStartPadding(
                            if (currentLang == "ar")
                                LayoutDirection.Rtl else LayoutDirection.Ltr
                        ),
                        bottom = innerPadding.calculateBottomPadding()
                    ),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    HorizontalPager(
                        state = pagerState,
                        modifier = Modifier.fillMaxHeight(0.78f)
                    ) { index ->
                        Column(
                            Modifier
                                .fillMaxWidth()
                                .padding(
                                    vertical = HorizontalPaddingSignIn,
                                    horizontal = SmallPadding
                                ),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(
                                text = stringResource(id = pages[index].title),
                                style = MaterialTheme.typography.titleSmall,
                                modifier = Modifier.padding(bottom = HorizontalPaddingSignIn)
                            )
                            Image(
                                painter = painterResource(id = pages[index].image),
                                contentDescription = null,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(300.dp),
                                contentScale = ContentScale.Fit,

                                )
                            Text(
                                text = stringResource(id = pages[index].body),
                                color = Color.DarkGray,
                                style = MaterialTheme.typography.displaySmall,
                                textAlign = TextAlign.Center,
                                modifier = Modifier.padding(horizontal = HorizontalPaddingSignIn),
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
                            navigator?.replaceAll(LoginScreen())
                        }
                    }) {
                        Text(
                            text = stringResource(id = R.string.confirm),
                            Modifier.padding(vertical = Dimens.ExtraSmallPadding),
                            style = MaterialTheme.typography.displaySmall.copy(
                                fontWeight = FontWeight.Bold
                            )
                        )
                    }
                }
            }
        }
    }
}