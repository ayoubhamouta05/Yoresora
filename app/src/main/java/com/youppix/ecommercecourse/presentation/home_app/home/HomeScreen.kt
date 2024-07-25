package com.youppix.ecommercecourse.presentation.home_app.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import cafe.adriel.voyager.core.screen.Screen
import com.youppix.ecommercecourse.R
import com.youppix.ecommercecourse.common.Dimens.ExtraSmallPadding
import com.youppix.ecommercecourse.common.Dimens.HorizontalPaddingSignIn
import com.youppix.ecommercecourse.common.Dimens.MediumPadding
import com.youppix.ecommercecourse.common.Dimens.SmallPadding
import com.youppix.ecommercecourse.presentation.home_app.components.CategoriesListItem
import com.youppix.ecommercecourse.presentation.home_app.components.CustomHorizontalPager
import com.youppix.ecommercecourse.presentation.home_app.components.CustomIconItem
import com.youppix.ecommercecourse.presentation.home_app.components.CustomSearchBar


class HomeScreen() : Screen {

    @Composable
    override fun Content() {
        Scaffold(
            modifier = Modifier.fillMaxSize()
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
                        CustomSearchBar(
                            modifier = Modifier
                                .weight(1f)

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
                                horizontal = HorizontalPaddingSignIn,
                                vertical = SmallPadding
                            ),
                        textAlign = TextAlign.Start
                    )
                }

                item {

                    LazyRow(
                        modifier = Modifier.fillMaxSize(),
                        contentPadding = PaddingValues(horizontal = ExtraSmallPadding),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        item {
                            CategoriesListItem(name = "All", selected = false) {}
                        }
                        item {
                            CategoriesListItem(name = "Men", selected = false) {}
                        }
                        item {
                            CategoriesListItem(name = "Women", selected = true) {}
                        }
                        item {
                            CategoriesListItem(name = "Kids", selected = false) {}
                        }
                    }
                }

                item {
                    CustomHorizontalPager(
                        images = arrayListOf(
                            R.drawable.onboarding3,
                            R.drawable.onboarding2,
                            R.drawable.onboarding1, R.drawable.onboarding3,
                            R.drawable.onboarding2,
                            R.drawable.onboarding1,
                        ),
                        modifier = Modifier.padding(top = MediumPadding)
                    )
                }

            }


        }
    }
}