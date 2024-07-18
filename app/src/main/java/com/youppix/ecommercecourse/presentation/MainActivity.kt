package com.youppix.ecommercecourse.presentation

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.res.Configuration
import android.os.Bundle
import android.util.Log
import android.window.OnBackInvokedDispatcher
import androidx.activity.ComponentActivity
import androidx.activity.OnBackPressedCallback
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.LayoutDirection
import androidx.fragment.app.FragmentActivity
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.lifecycleScope
import cafe.adriel.voyager.navigator.CurrentScreen
import cafe.adriel.voyager.navigator.Navigator
import com.youppix.ecommercecourse.R
import com.youppix.ecommercecourse.common.Constant
import com.youppix.ecommercecourse.common.Constant.APP_LANG
import com.youppix.ecommercecourse.common.CustomDialog
import com.youppix.ecommercecourse.common.LeavingAppDialog
import com.youppix.ecommercecourse.presentation.auth.login.LoginScreen
import com.youppix.ecommercecourse.presentation.selectLanguage.SelectLanguageScreen
import com.youppix.ecommercecourse.presentation.ui.theme.EcommerceCourseTheme
import dagger.hilt.android.AndroidEntryPoint
import java.util.Locale

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            val currentLang = LocalContext.current.getSharedPreferences(APP_LANG, 0)
                .getString(APP_LANG, Locale.getDefault().language) ?: "en"

            val viewModel = hiltViewModel<MainViewModel>()

            var backPressedState by remember {
                mutableStateOf(false)
            }
            var showDialog by remember {
                mutableStateOf(false)
            }
            onBackButtonPressed {
                showDialog = !backPressedState
                backPressedState
            }
            EcommerceCourseTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                ) {
                    Navigator(
                        if (viewModel.appEntry.value)
                            LoginScreen()
                        else
                            SelectLanguageScreen()
                    ) { navigator ->
                        CurrentScreen()
                        backPressedState = navigator.canPop
                    }

                    LeavingAppDialog(currentLang = currentLang,
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
    }

    private fun onBackButtonPressed(callback: (() -> Boolean)) {
        onBackPressedDispatcher.addCallback(
            this@MainActivity,
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    if (callback()) {
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

