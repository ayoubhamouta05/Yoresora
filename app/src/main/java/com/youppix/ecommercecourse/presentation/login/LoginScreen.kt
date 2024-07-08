package com.youppix.ecommercecourse.presentation.login

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import com.youppix.ecommercecourse.common.Constant.APP_LANG
import com.youppix.ecommercecourse.presentation.ui.theme.EcommerceCourseTheme
import java.util.Locale

class LoginScreen() : Screen {
    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    override fun Content() {
        val isEnglish = LocalContext.current.getSharedPreferences(APP_LANG, 0)
            .getString(APP_LANG, Locale.getDefault().language) == "en"


        Scaffold(modifier = Modifier.fillMaxSize(),
            topBar = {
                TopAppBar(title = {
                    Text(
                        text = "Sing in",
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center,
                        style = MaterialTheme.typography.titleSmall.copy(fontSize = 24.sp),
                        color = Color.Gray
                    )
                })

            }) { innerPadding ->
            CompositionLocalProvider(
                if (isEnglish) {
                    LocalLayoutDirection provides LayoutDirection.Ltr
                } else {
                    LocalLayoutDirection provides LayoutDirection.Rtl
                }
            ) {
                Column(
                    modifier = Modifier.padding(
                        bottom = innerPadding.calculateBottomPadding()
                    )
                ) {

                    Text(
                        text = "Welcome Back",
                        style = MaterialTheme.typography.displayMedium.copy(fontWeight = FontWeight.Bold),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = innerPadding.calculateTopPadding()),
                        textAlign = TextAlign.Center
                    )
                    Text(
                        text = "Sign in with your email and password \nor continue with social media",
                        style = MaterialTheme.typography.bodyMedium,
                        color = Color.Gray,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 30.dp),
                        textAlign = TextAlign.Center
                    )

                    OutlinedTextField(
                        modifier = Modifier
                            .align(Alignment.CenterHorizontally)
                            .fillMaxWidth()
                            .padding(horizontal = 30.dp, vertical = 30.dp),
                        value = "",
                        label = {
                            Text(text = "Email")
                        },
                        shape = RoundedCornerShape(50),
                        trailingIcon = {
                            Icon(Icons.Outlined.Email, contentDescription = null)
                        },
                        onValueChange = { value ->

                        }
                    )

                    OutlinedTextField(
                        modifier = Modifier
                            .align(Alignment.CenterHorizontally)
                            .fillMaxWidth()
                            .padding(horizontal = 30.dp),
                        value = "",
                        label = {
                            Text(text = "Password")
                        },
                        shape = RoundedCornerShape(50),
                        trailingIcon = {
                            Icon(Icons.Outlined.Lock, contentDescription = null)
                        },
                        onValueChange = { value ->

                        }
                    )
                }

            }
        }
    }
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun LoginScreenPreview() {
    EcommerceCourseTheme {
        LoginScreen().Content()
    }
}