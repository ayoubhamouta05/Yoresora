package com.youppix.ecommercecourse.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.navigator.Navigator
import com.youppix.ecommercecourse.presentation.onBoarding.OnBoardingScreen
import com.youppix.ecommercecourse.presentation.ui.theme.EcommerceCourseTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            EcommerceCourseTheme {
                Scaffold(modifier = Modifier.fillMaxSize(),
                   ) { innerPadding ->
                    Navigator(screen = OnBoardingScreen(
                        modifier = Modifier.padding(
                            top = innerPadding.calculateTopPadding(),
                            end = innerPadding.calculateEndPadding(LayoutDirection.Ltr),
                            start = innerPadding.calculateStartPadding(LayoutDirection.Ltr),
                            bottom = innerPadding.calculateBottomPadding())
                    ))

                }
            }
        }
    }
}

