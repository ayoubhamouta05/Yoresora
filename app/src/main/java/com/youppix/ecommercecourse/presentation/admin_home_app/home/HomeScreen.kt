package com.youppix.ecommercecourse.presentation.admin_home_app.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.hilt.getNavigatorScreenModel
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.youppix.ecommercecourse.R
import com.youppix.ecommercecourse.common.Dimens
import com.youppix.ecommercecourse.common.Dimens.BottomBarHeight
import com.youppix.ecommercecourse.common.Dimens.LargePadding
import com.youppix.ecommercecourse.common.Dimens.MediumPadding
import com.youppix.ecommercecourse.common.Dimens.SmallPadding
import com.youppix.ecommercecourse.presentation.admin_home_app.details.DetailsScreen
import com.youppix.ecommercecourse.presentation.admin_home_app.home.components.StatisticsGraph
import com.youppix.ecommercecourse.presentation.components.FlashSaleItemShimmerEffect
import com.youppix.ecommercecourse.presentation.user_home_app.home.components.CustomHorizontalPagerFlashSale

class HomeScreen : Screen {
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        val viewModel = navigator.getNavigatorScreenModel<HomeViewModel>()
        val state = viewModel.state.value

        val lazyListState = rememberLazyListState()


        Scaffold(
            modifier = Modifier.fillMaxSize(),
            topBar = {
                Row(
                    Modifier
                        .fillMaxWidth()
                        .padding(horizontal = MediumPadding, vertical = SmallPadding),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {


                }
            }
        ) { innerPadding ->

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
            ) {
                LazyColumn(
                    modifier = Modifier,
                    state = lazyListState,
                ) {
                    item {
                        Text(
                            text = stringResource(id = R.string.app_name),
                            style = MaterialTheme.typography.displayMedium.copy(
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.primary
                            ),
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(
                                    horizontal = Dimens.MediumPadding,
                                    vertical = Dimens.ExtraSmallPadding
                                ),
                            textAlign = TextAlign.Center
                        )
                    }

                    item {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = MediumPadding),
                        ) {
                            Column(
                                modifier = Modifier.weight(1f),
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Text(
                                    text = stringResource(R.string.numberOfItemsInApp),
                                    style = MaterialTheme.typography.bodyMedium.copy(
                                        fontWeight = FontWeight.Bold
                                    ),
                                    textAlign = TextAlign.Center,
                                    modifier = Modifier.padding(horizontal = Dimens.MediumPadding),
                                    maxLines = 1,
                                    overflow = TextOverflow.Ellipsis
                                )
                                Text(
                                    text = "9",
                                    style = MaterialTheme.typography.titleMedium.copy(
                                        fontWeight = FontWeight.SemiBold
                                    ),
                                    textAlign = TextAlign.Start,
                                    modifier = Modifier
                                        .padding(horizontal = Dimens.MediumPadding),
                                    maxLines = 1,
                                    overflow = TextOverflow.Ellipsis

                                )
                            }
                            Column(
                                modifier = Modifier.weight(1f),
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Text(
                                    text = stringResource(R.string.numberOfOrders),
                                    style = MaterialTheme.typography.bodyMedium.copy(
                                        fontWeight = FontWeight.Bold
                                    ),
                                    textAlign = TextAlign.Center,
                                    modifier = Modifier.padding(horizontal = Dimens.MediumPadding)
                                )
                                Text(
                                    text = "9",
                                    style = MaterialTheme.typography.titleMedium.copy(
                                        fontWeight = FontWeight.SemiBold
                                    ),
                                    textAlign = TextAlign.Start,
                                    modifier = Modifier.padding(horizontal = Dimens.MediumPadding),
                                    maxLines = 1,
                                    overflow = TextOverflow.Ellipsis
                                )
                            }
                            Column(
                                modifier = Modifier.weight(1.1f),
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Text(
                                    text = stringResource(R.string.numberOfSubscribers),
                                    style = MaterialTheme.typography.bodyMedium.copy(
                                        fontWeight = FontWeight.Bold
                                    ),
                                    textAlign = TextAlign.Center,
                                    modifier = Modifier.padding(horizontal = Dimens.MediumPadding)
                                )
                                Text(
                                    text = "300",
                                    style = MaterialTheme.typography.titleMedium.copy(
                                        fontWeight = FontWeight.SemiBold
                                    ),
                                    textAlign = TextAlign.Start,
                                    modifier = Modifier.padding(horizontal = Dimens.MediumPadding),
                                    maxLines = 1,
                                    overflow = TextOverflow.Ellipsis
                                )
                            }
                        }

                    }

                    item {
                        Text(
                            text = stringResource(R.string.ordersAwaited),
                            style = MaterialTheme.typography.bodyMedium.copy(
                                fontWeight = FontWeight.Bold
                            ),
                            textAlign = TextAlign.Center,
                            modifier = Modifier
                                .padding(horizontal = Dimens.MediumPadding)
                                .fillMaxWidth()
                        )

                        Text(
                            text = "4",
                            style = MaterialTheme.typography.titleMedium.copy(
                                fontWeight = FontWeight.SemiBold
                            ),
                            textAlign = TextAlign.Center,
                            modifier = Modifier
                                .padding(horizontal = Dimens.MediumPadding)
                                .padding(bottom = SmallPadding)
                                .fillMaxWidth(),
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                    }

                    item {
                        Text(
                            text = stringResource(id = R.string.bestSellingItems),
                            style = MaterialTheme.typography.titleMedium.copy(
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.primary
                            ),
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(
                                    horizontal = Dimens.MediumPadding,
                                    vertical = Dimens.ExtraSmallPadding
                                ),
                            textAlign = TextAlign.Center
                        )

                        if (state.isLoading) {
                            FlashSaleItemShimmerEffect()
                        } else {
                            CustomHorizontalPagerFlashSale(
                                items = state.bestSellingItems,
                            ) { itemSelected ->
                                navigator.push(DetailsScreen("0" , itemSelected))
                            }
                        }
                    }

                    item {
                        Text(
                            text = stringResource(id = R.string.lastWeekStatistics),
                            style = MaterialTheme.typography.titleMedium.copy(
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.primary
                            ),
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(
                                    horizontal = Dimens.MediumPadding,
                                    vertical = Dimens.ExtraSmallPadding
                                ),
                            textAlign = TextAlign.Center
                        )

                        Spacer(Modifier.height(MediumPadding))

                        StatisticsGraph(
                            Modifier
                                .fillMaxWidth()
                                .padding(horizontal = MediumPadding)
                                .padding(bottom = BottomBarHeight + LargePadding)
                                .height(200.dp)


                        )
                    }
                }
            }


        }

    }
}