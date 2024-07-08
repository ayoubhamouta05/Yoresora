package com.youppix.ecommercecourse.presentation

import android.content.Context
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import com.youppix.ecommercecourse.R
import com.youppix.ecommercecourse.common.Constant.APP_LANG
import com.youppix.ecommercecourse.presentation.components.CustomButton
import com.youppix.ecommercecourse.presentation.onBoarding.OnBoardingScreen

class SelectLanguageScreen(private val modifier: Modifier=Modifier,private val viewModel: MainViewModel, private val baseContext: Context) :
    Screen {
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.current
        Column(
            modifier = modifier.fillMaxSize(),
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
                navigator?.push(OnBoardingScreen(modifier))
            }

            CustomButton(
                modifier = Modifier
                    .padding(vertical = 10.dp)
                    .width(100.dp),
                text = stringResource(id = R.string.arabic)
            ) {
                viewModel.saveLanguage(APP_LANG, "ar")
                navigator?.push(OnBoardingScreen(modifier))
            }

        }
    }
}
