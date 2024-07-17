package com.youppix.ecommercecourse.presentation

import android.annotation.SuppressLint
import android.content.Context
import android.content.res.Configuration
import android.os.Bundle
import android.util.Log
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
import com.youppix.ecommercecourse.common.Constant
import com.youppix.ecommercecourse.common.Constant.APP_LANG
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
            EcommerceCourseTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                ) {
                    Navigator(
                        SelectLanguageScreen()
                    )
                }
            }
        }
    }
}

