package com.youppix.ecommercecourse.presentation.home_app

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.OnBackPressedCallback
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.transitions.FadeTransition
import com.youppix.ecommercecourse.common.Constant
import com.youppix.ecommercecourse.common.Constant.APP_LANG
import com.youppix.ecommercecourse.common.Constant.setLocal
import com.youppix.ecommercecourse.common.Dimens.BottomBarHeight
import com.youppix.ecommercecourse.common.Dimens.MediumPadding
import com.youppix.ecommercecourse.presentation.components.LeavingAppDialog
import com.youppix.ecommercecourse.presentation.components.StatusBarColor
import com.youppix.ecommercecourse.presentation.home_app.chat.ChatScreen
import com.youppix.ecommercecourse.presentation.home_app.components.CustomBottomBar
import com.youppix.ecommercecourse.presentation.home_app.components.shadow
import com.youppix.ecommercecourse.presentation.home_app.details.DetailsScreen
import com.youppix.ecommercecourse.presentation.home_app.details.customSize.CustomSizeScreen
import com.youppix.ecommercecourse.presentation.home_app.favorites.FavoritesScreen
import com.youppix.ecommercecourse.presentation.home_app.home.HomeScreen
import com.youppix.ecommercecourse.presentation.home_app.profile.ProfileScreen
import com.youppix.ecommercecourse.presentation.home_app.shop.ShopScreen
import com.youppix.ecommercecourse.presentation.ui.theme.EcommerceCourseTheme
import dagger.hilt.android.AndroidEntryPoint
import java.util.Locale

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private var navigator: Navigator? = null

    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        val currentLang =
            getSharedPreferences(APP_LANG, 0).getString(APP_LANG, Locale.getDefault().language)
                ?: Locale.getDefault().language

        setLocal(currentLang, this)

        val userId = getSharedPreferences(Constant.APP_ENTRY, 0).getString("userId", "")

        setContent {
            val viewModel: MainActivityViewModel = hiltViewModel()
            val state = viewModel.state.value

            var backPressedState by remember {
                mutableStateOf(false)
            }
            var showDialog by remember {
                mutableStateOf(false)
            }
            var showBottomBar by remember {
                mutableStateOf(true)
            }

            onBackButtonPressed {
                showDialog = !backPressedState
                if (navigator!!.lastItem.javaClass.name != HomeScreen::class.java.name) {
                    navigator!!.replace(HomeScreen())
                    showDialog = false
                } else {
                    showDialog = true
                }
            }

            EcommerceCourseTheme {
                StatusBarColor()
                LaunchedEffect(navigator?.items) {
                    navigator?.let {
                        viewModel.apply {
                            when (navigator!!.lastItem::class.java.simpleName) {
                                HomeScreen::class.java.simpleName -> setCurrentScreen(0)
                                ShopScreen::class.java.simpleName -> setCurrentScreen(1)
                                FavoritesScreen::class.java.simpleName -> setCurrentScreen(2)
                                ChatScreen::class.java.simpleName -> setCurrentScreen(3)
                                ProfileScreen::class.java.simpleName -> setCurrentScreen(4)
                            }
                        }
                    }
                }
                Scaffold(
                    bottomBar = {
                        if (showBottomBar) {
                            CustomBottomBar(
                                state.currentScreen,
                                modifier = Modifier.padding(
                                    start = MediumPadding,
                                    end = MediumPadding,
                                    bottom = MediumPadding
                                )
                            ) {
                                when (it) {
                                    0 -> {
                                        if (navigator?.lastItem?.javaClass?.name != HomeScreen::class.java.name)
                                            navigator?.replaceAll(HomeScreen())
                                    }

                                    1 -> {
                                        if (navigator?.lastItem?.javaClass?.name != ShopScreen::class.java.name)
                                            navigator?.replace(ShopScreen())
                                    }

                                    2 -> {
                                        if (navigator?.lastItem?.javaClass?.name != FavoritesScreen::class.java.name)
                                            navigator?.replace(FavoritesScreen(userId = userId))
                                    }

                                    3 -> {
                                        if (navigator?.lastItem?.javaClass?.name != ChatScreen::class.java.name)
                                            navigator?.replace(ChatScreen())
                                    }

                                    4 -> {
                                        if (navigator?.lastItem?.javaClass?.name != ProfileScreen::class.java.name)
                                            navigator?.replace(ProfileScreen())
                                    }
                                }
                                viewModel.setCurrentScreen(it)
                            }
                        }
                    }
                ) {
                    Box(modifier = Modifier.fillMaxSize()) {
                        Navigator(screen = HomeScreen()) { navigator ->
                            this@MainActivity.navigator = navigator
                            FadeTransition(navigator = navigator)
                            showBottomBar =
                                navigator.lastItem.javaClass.name != DetailsScreen::class.java.name &&
                                        navigator.lastItem.javaClass.name != CustomSizeScreen::class.java.name
                            backPressedState =
                                navigator.lastItem.javaClass.name != HomeScreen::class.java.name
                        }
                        if (showBottomBar) {
                            Spacer(
                                modifier = Modifier
                                    .height(BottomBarHeight)
                                    .fillMaxWidth()
                                    .align(Alignment.BottomCenter)
                                    .shadow(
                                        MaterialTheme.colorScheme.background,
                                        offsetY = BottomBarHeight,
                                        spread = MediumPadding * 2,
                                        blurRadius = (MediumPadding.value * 1.5).dp
                                    )
                            )
                        }
                    }

                }

                LeavingAppDialog(
                    showDialog = showDialog,
                    onConfirmRequest = {
                        finishAffinity()
                        showDialog = false
                    },
                    onDismissRequest = {
                        showDialog = false
                    }
                )
            }
        }
    }


    private fun onBackButtonPressed(onBackPressed: () -> Unit) {
        onBackPressedDispatcher.addCallback(
            this@MainActivity,
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    onBackPressed()
                }
            }
        )
    }

}