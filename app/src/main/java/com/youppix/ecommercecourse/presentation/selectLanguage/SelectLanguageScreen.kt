package com.youppix.ecommercecourse.presentation.selectLanguage

import android.content.Context
import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import com.youppix.ecommercecourse.R
import com.youppix.ecommercecourse.common.Constant.APP_LANG
import com.youppix.ecommercecourse.presentation.components.CustomButton
import com.youppix.ecommercecourse.presentation.onBoarding.OnBoardingScreen
import java.util.Locale

class SelectLanguageScreen() :
    Screen {
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.current
        val viewModel: SelectLanguageViewModel = hiltViewModel()
        viewModel.getLanguage(APP_LANG, Locale.getDefault().language)
        val currentLang = viewModel.language.value
        val context = LocalContext.current
        setLocal(currentLang , context)
        Scaffold(
            modifier = Modifier.fillMaxSize(),
        ) { innerPadding ->
            Column(
                Modifier
                    .fillMaxSize()
                    .padding(
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
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = stringResource(id = R.string.selectLanguage),
                    style = MaterialTheme.typography.titleSmall,
                    textAlign = TextAlign.Center
                )

                CustomButton(
                    modifier = Modifier
                        .padding(top = 10.dp)
                        .width(100.dp),
                    text = stringResource(id = R.string.english),
                ) {
                    viewModel.saveLanguage(APP_LANG, "en")
                    setLocal("en" , context)
                    navigator?.push(OnBoardingScreen())
                }

                CustomButton(
                    modifier = Modifier
                        .padding(vertical = 10.dp)
                        .width(100.dp),
                    text = stringResource(id = R.string.arabic)
                ) {
                    viewModel.saveLanguage(APP_LANG, "ar")
                    setLocal("en" , context)
                    navigator?.push(OnBoardingScreen())
                }

            }
        }
    }

    private fun setLocal(lang : String , context: Context){
        val locale = Locale(lang)
        Locale.setDefault(locale)
        val config = Configuration()

        config.setLocale(locale)
        context.resources.updateConfiguration(
            config,
            context.resources.displayMetrics
        )
    }
}

