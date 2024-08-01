package com.youppix.ecommercecourse.presentation.home_app

import android.annotation.SuppressLint
import android.os.Bundle
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.transitions.SlideTransition
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
            val viewModel: MainActivityViewModel = hiltViewModel()
            val state = viewModel.state.value

            var backPressedState by remember {
                mutableStateOf(false)
            }
            var showDialog by remember {
                mutableStateOf(false)
            }

            onBackButtonPressed {
                showDialog = !backPressedState
                if ( navigator!!.lastItem.javaClass.name != HomeScreen::class.java.name ){
                    navigator!!.replace(HomeScreen())
                    showDialog = false
                }else{
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
                        CustomBottomBar(
                            state.currentScreen,
                            modifier = Modifier.padding(
                                start = MediumPadding,
                                end = MediumPadding,
                                bottom = MediumPadding
                            )
                        ) {
                            if (state.currentScreen != it){
                                when (it) {
                                    0 -> navigator?.replace(HomeScreen())
                                    1 -> navigator?.replace(ShopScreen())
                                    2 -> navigator?.replace(FavoritesScreen())
                                    3 -> navigator?.replace(ChatScreen())
                                    4 -> navigator?.replace(ProfileScreen())
                                }
                                viewModel.setCurrentScreen(it)
                            }


                        }
                    }
                ) {
                    Box(modifier = Modifier.fillMaxSize()) {
                        Navigator(screen = HomeScreen()) { navigator ->
                            this@MainActivity.navigator = navigator
                            SlideTransition(navigator = navigator)
                            backPressedState = navigator.lastItem.javaClass.name != HomeScreen::class.java.name
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