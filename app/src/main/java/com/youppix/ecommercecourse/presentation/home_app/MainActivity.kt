package com.youppix.ecommercecourse.presentation.home_app

import android.annotation.SuppressLint
import android.app.Activity
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.OnBackPressedCallback
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.view.WindowCompat
import cafe.adriel.voyager.navigator.CurrentScreen
import cafe.adriel.voyager.navigator.Navigator
import com.youppix.ecommercecourse.common.Constant.APP_ENTRY
import com.youppix.ecommercecourse.common.Constant.APP_LANG
import com.youppix.ecommercecourse.common.Constant.setLocal
import com.youppix.ecommercecourse.common.Dimens.BottomBarHeight
import com.youppix.ecommercecourse.common.Dimens.MediumPadding
import com.youppix.ecommercecourse.presentation.components.LeavingAppDialog
import com.youppix.ecommercecourse.presentation.components.StatusBarColor
import com.youppix.ecommercecourse.presentation.home_app.chat.ChatScreen
import com.youppix.ecommercecourse.presentation.home_app.components.CustomBottomBar
import com.youppix.ecommercecourse.presentation.home_app.components.shadow
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


        setContent {

            val activity = LocalContext.current as Activity
            val backgroundArgb = MaterialTheme.colorScheme.background.toArgb()
            activity.window.statusBarColor = backgroundArgb
            val wic = WindowCompat.getInsetsController(window, window.decorView)
            wic.isAppearanceLightStatusBars = true // Adapt it with your implementation

            var backPressedState by remember {
                mutableStateOf(false)
            }
            var showDialog by remember {
                mutableStateOf(false)
            }

            var currentScreen by remember {
                mutableIntStateOf(0)
            }

            onBackButtonPressed {
                showDialog = !backPressedState
                backPressedState
            }

            EcommerceCourseTheme {
                StatusBarColor()
                LaunchedEffect(currentScreen) {
                    when (currentScreen) {
                        0 -> navigator?.replaceAll(HomeScreen())
                        1 -> navigator?.push(ShopScreen())
                        2 -> navigator?.push(FavoritesScreen())
                        3 -> navigator?.push(ChatScreen())
                        4 -> navigator?.push(ProfileScreen())
                    }
                }
                LaunchedEffect(navigator?.items) {

                    navigator?.let {
                        when (navigator!!.lastItem::class.java.simpleName) {
                            HomeScreen::class.java.simpleName -> currentScreen = 0
                            ShopScreen::class.java.simpleName -> currentScreen = 1
                            FavoritesScreen::class.java.simpleName -> currentScreen = 2
                            ChatScreen::class.java.simpleName -> currentScreen = 3
                            ProfileScreen::class.java.simpleName -> currentScreen = 4
                        }
                    }
                }
                Scaffold(
                    bottomBar = {
                        CustomBottomBar(
                            currentScreen,
                            modifier = Modifier.padding(
                                start = MediumPadding,
                                end = MediumPadding,
                                bottom = MediumPadding
                            )
                        ) {
                            currentScreen = it
                        }
                    }
                ) {
                    Box(modifier = Modifier.fillMaxSize()) {
                        Navigator(screen = HomeScreen(),
                            onBackPressed = {
                                if(navigator?.canPop == true){
                                    navigator?.replaceAll(HomeScreen())
                                }
                                true
                            }
                        ) { navigator ->
                            this@MainActivity.navigator = navigator
                            CurrentScreen()
                            backPressedState = navigator.canPop
                        }
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


    private fun onBackButtonPressed(callback: (() -> Boolean)) {
        onBackPressedDispatcher.addCallback(
            this@MainActivity,
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    if (callback()) {
                        navigator?.pop()
                        remove()
                        performBackPress()
                    }
                }
            })
    }

    fun performBackPress() {
        onBackPressedDispatcher.onBackPressed()
    }


}