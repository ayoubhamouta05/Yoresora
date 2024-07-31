package com.youppix.ecommercecourse.presentation.starting_app

import com.youppix.ecommercecourse.presentation.components.NetworkStatusMessage
import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.OnBackPressedCallback
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import cafe.adriel.voyager.navigator.CurrentScreen
import cafe.adriel.voyager.navigator.Navigator
import com.youppix.ecommercecourse.common.Constant.APP_LANG
import com.youppix.ecommercecourse.common.Constant.setLocal
import com.youppix.ecommercecourse.presentation.components.LeavingAppDialog
import com.youppix.ecommercecourse.presentation.components.StatusBarColor
import com.youppix.ecommercecourse.presentation.home_app.MainActivity
import com.youppix.ecommercecourse.presentation.starting_app.auth.login.LoginScreen
import com.youppix.ecommercecourse.presentation.starting_app.selectLanguage.SelectLanguageScreen
import com.youppix.ecommercecourse.presentation.ui.theme.EcommerceCourseTheme
import dagger.hilt.android.AndroidEntryPoint
import java.util.Locale

@AndroidEntryPoint
class StartActivity : ComponentActivity() {

    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            StatusBarColor()
            val viewModel = hiltViewModel<StartActivityViewModel>()
            if (viewModel.appEntry.value == "2") {
                val intent = Intent(this@StartActivity , MainActivity::class.java)
                startActivity(intent)
                finishAffinity()
            }else{

                setLanguage(LocalContext.current)

                val status = viewModel.status.value
                val showNetworkStatus = viewModel.showNetworkStatus.value

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
                    StatusBarColor()
                    Scaffold(
                        topBar = {
                            if (showNetworkStatus) {
                                NetworkStatusMessage(status = status)
                            }
                        },
                        modifier = Modifier.fillMaxSize(),
                    ) {
                        Navigator(
                            if (viewModel.appEntry.value == "1")
                                LoginScreen()
                            else
                                SelectLanguageScreen()
                        ) { navigator ->
                            CurrentScreen()
                            backPressedState = navigator.canPop
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


        }
    }

    private fun setLanguage(current: Context) {
        val currentLang = current.getSharedPreferences(APP_LANG, 0)
            .getString(APP_LANG, Locale.getDefault().language) ?: "en"
        setLocal(currentLang  , this)
    }

    private fun onBackButtonPressed(callback: (() -> Boolean)) {
        onBackPressedDispatcher.addCallback(
            this@StartActivity,
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

