package com.youppix.ecommercecourse.presentation.admin_home_app.users

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.hilt.getNavigatorScreenModel
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.youppix.ecommercecourse.R
import com.youppix.ecommercecourse.common.Dimens.BottomBarHeight
import com.youppix.ecommercecourse.common.Dimens.ExtraSmallPadding
import com.youppix.ecommercecourse.common.Dimens.HorizontalPagerCardHeight
import com.youppix.ecommercecourse.common.Dimens.MediumPadding
import com.youppix.ecommercecourse.common.Dimens.SmallPadding
import com.youppix.ecommercecourse.presentation.admin_home_app.users.components.UserItem
import com.youppix.ecommercecourse.presentation.components.CustomTopAppBar
import com.youppix.ecommercecourse.presentation.user_home_app.home.HomeScreen
import java.util.Locale

class UsersScreen : Screen {
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        val viewModel = navigator.getNavigatorScreenModel<UsersViewModel>()
        val state by viewModel.state

        val isArabic = Locale.getDefault().language == "ar"

        Scaffold(
            modifier = Modifier.fillMaxSize(),
            topBar = {
                CustomTopAppBar(title = stringResource(id = R.string.users),
                    isArabic = isArabic,
                    onBackClicked = {
                        if (navigator.canPop) {
                            navigator.pop()
                        } else {
                            navigator.replace(HomeScreen())
                        }
                    })
            },
        ) { innerPadding ->
            LazyColumn(modifier = Modifier.padding(innerPadding)) {
                items(state.users.size) { index ->

                    UserItem(
                        modifier = Modifier
                            .padding(bottom = if (index == state.users.lastIndex) BottomBarHeight.plus(
                                MediumPadding) else 0.dp)
                            .padding(vertical = SmallPadding, horizontal =  MediumPadding)

                            .clip(shape = RoundedCornerShape(
                                SmallPadding
                            ))
                            .clickable {  }
                            .background(
                                color = MaterialTheme.colorScheme.primary.copy(alpha = 0.2f)
                            )
                            .padding(ExtraSmallPadding)

                            ,
                        user = state.users[index]
                    )

                }
            }

        }

    }
}