package com.youppix.ecommercecourse.presentation.activities.homeActivity

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.youppix.ecommercecourse.common.Constant.APP_ENTRY
import com.youppix.ecommercecourse.common.Constant.APP_LANG
import com.youppix.ecommercecourse.common.Constant.setLocal
import com.youppix.ecommercecourse.presentation.ui.theme.EcommerceCourseTheme
import java.util.Locale

class HomeActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()
        val currentLang =
            getSharedPreferences(APP_LANG, 0).getString(APP_LANG, Locale.getDefault().language)
                ?: Locale.getDefault().language
        val currentUser =
            getSharedPreferences(APP_ENTRY, 0).getString("userName", "null")
        setLocal(currentLang, this)
        setContent {
            EcommerceCourseTheme {
                Column(
                    Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = currentUser.toString(),
                        Modifier
                            .fillMaxSize()
                            .align(Alignment.CenterHorizontally),
                        textAlign = TextAlign.Center,
                        fontSize = 32.sp
                    )
                }
            }

        }
    }
}