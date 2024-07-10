package com.youppix.ecommercecourse.presentation

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.LayoutDirection
import androidx.hilt.navigation.compose.hiltViewModel
import cafe.adriel.voyager.navigator.Navigator
import com.youppix.ecommercecourse.common.Constant.APP_LANG
import com.youppix.ecommercecourse.presentation.selectLanguage.SelectLanguageScreen
import com.youppix.ecommercecourse.presentation.ui.theme.EcommerceCourseTheme
import dagger.hilt.android.AndroidEntryPoint
import java.util.Locale

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val viewModel: MainViewModel = hiltViewModel()
            viewModel.getLanguage(APP_LANG, Locale.getDefault().language)
            val currentLang = viewModel.language.value
            val locale = Locale(currentLang)
            Locale.setDefault(locale)
            val config = Configuration()

            config.setLocale(locale)
            baseContext.resources.updateConfiguration(
                config,
                baseContext.resources.displayMetrics
            )

            EcommerceCourseTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                ) { innerPadding ->
                    Navigator(
                        SelectLanguageScreen(
                            Modifier.padding(
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
                            viewModel = viewModel ,
                            baseContext = baseContext
                        )
                    )

                }
            }
        }
    }
}

