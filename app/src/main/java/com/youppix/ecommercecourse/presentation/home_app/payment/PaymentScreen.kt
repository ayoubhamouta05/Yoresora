package com.youppix.ecommercecourse.presentation.home_app.payment

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Bitmap
import android.util.Log
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.material3.SnackbarVisuals
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.viewinterop.AndroidView
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.hilt.getNavigatorScreenModel
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.youppix.ecommercecourse.R
import com.youppix.ecommercecourse.common.Constant
import com.youppix.ecommercecourse.common.Dimens.LargePadding
import com.youppix.ecommercecourse.common.Dimens.SmallPadding
import com.youppix.ecommercecourse.common.Dimens.SocialMediaItemSize
import com.youppix.ecommercecourse.domain.manager.NetworkConnectivityManager
import com.youppix.ecommercecourse.presentation.components.CustomCircularProgress
import com.youppix.ecommercecourse.presentation.components.CustomTopAppBar
import com.youppix.ecommercecourse.presentation.home_app.MainActivity
import com.youppix.ecommercecourse.presentation.home_app.components.CustomIcon
import com.youppix.ecommercecourse.presentation.home_app.home.HomeScreen
import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.util.Locale

data class PaymentScreen(var url: String = "https://www.google.com") : Screen {
    @SuppressLint("SetJavaScriptEnabled")
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        val viewModel: PaymentViewModel = navigator.getNavigatorScreenModel()
        val state by viewModel.state
        val context = LocalContext.current
        val isArabic = Locale.getDefault().language == "ar"
        val scope = rememberCoroutineScope()

        val webView = remember { WebView(context) }
        var currentUrl by remember { mutableStateOf(url) }

        val snackbarHostState = remember { SnackbarHostState() }

        LaunchedEffect(Unit) {
            setLanguage(context)
            webView.settings.javaScriptEnabled = true
        }

        var firstTimeShown by remember { mutableStateOf(false) }

        LaunchedEffect(state.networkState) {
            scope.launch {
                if (state.networkState == NetworkConnectivityManager.Status.Available && !firstTimeShown) {
                    snackbarHostState.currentSnackbarData?.dismiss()
                    firstTimeShown = true
                } else {
                    snackbarHostState.currentSnackbarData?.dismiss()
                    snackbarHostState.showSnackbar(
                        message = if (state.networkState == NetworkConnectivityManager.Status.Available) {
                            context.getString(R.string.networkStatusAvailable)
                        } else {
                            context.getString(R.string.networkStatusUnavailable)
                        },
                        duration = if (state.networkState == NetworkConnectivityManager.Status.Available) {
                            SnackbarDuration.Short
                        } else {
                            SnackbarDuration.Indefinite
                        }
                    )

                }
            }
        }

        onBackButtonPressed(context, webView)
        {
            if (webView.canGoBack()) {
                webView.goBack()
            } else {
                if (navigator.canPop) {
                    navigator.pop()
                } else {
                    navigator.replace(HomeScreen())
                }
            }
        }

        Scaffold(
            modifier = Modifier.fillMaxSize(),
            topBar = {
                CustomTopAppBar(
                    title = stringResource(id = R.string.checkout),
                    isArabic = isArabic,
                    actions = {
                        CustomIcon(
                            imagePainter = painterResource(id = R.drawable.ic_refresh)
                        ) {
                            webView.reload()
                            viewModel.onEvent(PaymentEvent.ToggleLoading(true))
                        }
                    },
                    onBackClicked = {
                        if (webView.canGoBack()) {
                            webView.goBack()
                        } else {
                            if (navigator.canPop) {
                                navigator.pop()
                            } else {
                                navigator.replace(HomeScreen())
                            }
                        }
                    },
                )
            },
            snackbarHost =
            {
                SnackbarHost(hostState = snackbarHostState) {
                    Snackbar(
                        modifier = Modifier.padding(horizontal = LargePadding),
                        snackbarData = it,
                        containerColor = if (state.networkState ==
                            NetworkConnectivityManager.Status.Available
                        ) Color.Green
                        else MaterialTheme.colorScheme.error,
                        actionColor = MaterialTheme.colorScheme.background,
                        actionContentColor = MaterialTheme.colorScheme.background,
                        contentColor = if(state.networkState == NetworkConnectivityManager.Status.Available)MaterialTheme.colorScheme.onBackground else MaterialTheme.colorScheme.background,
                        shape = RoundedCornerShape(
                            topStart = LargePadding,
                            bottomEnd = LargePadding,
                            bottomStart = LargePadding
                        )
                    )
                }

            }
        )
        { innerPadding ->


            AndroidView(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding),
                factory = {
                    webView.apply {
                        layoutParams = ViewGroup.LayoutParams(
                            ViewGroup.LayoutParams.MATCH_PARENT,
                            ViewGroup.LayoutParams.MATCH_PARENT
                        )

                        webViewClient = object : WebViewClient() {
                            override fun onPageStarted(
                                view: WebView?,
                                url: String?,
                                favicon: Bitmap?,
                            ) {
                                url?.let {
                                    currentUrl = it
                                    viewModel.onEvent(PaymentEvent.ToggleLoading(true))
                                }
                            }

                            override fun onPageFinished(view: WebView?, url: String?) {
                                viewModel.onEvent(PaymentEvent.ToggleLoading(false))
                            }
                        }
                    }
                },
                update = { it.loadUrl(currentUrl) }
            )

            CustomCircularProgress(isLoading = state.isLoading)

        }
    }

    private fun setLanguage(current: Context) {
        val currentLang = current.getSharedPreferences(Constant.APP_LANG, 0)
            .getString(Constant.APP_LANG, Locale.getDefault().language) ?: "en"
        Constant.setLocal(currentLang, current)
    }

    private fun onBackButtonPressed(context: Context, webView: WebView, onBackPressed: () -> Unit) {
        (context as MainActivity).onBackPressedDispatcher.addCallback(
            context,
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    if (webView.canGoBack()) {
                        webView.goBack()
                    } else {
                        onBackPressed()
                        remove()
                    }

                }
            }
        )
    }
}

