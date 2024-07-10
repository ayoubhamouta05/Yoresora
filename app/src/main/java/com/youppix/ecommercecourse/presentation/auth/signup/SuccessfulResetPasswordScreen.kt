package com.youppix.ecommercecourse.presentation.auth.signup

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import com.youppix.ecommercecourse.R
import com.youppix.ecommercecourse.common.Dimens
import com.youppix.ecommercecourse.common.Dimens.HorizontalPaddingSignIn
import com.youppix.ecommercecourse.presentation.auth.login.LoginScreen

class SuccessfulSignUpScreen : Screen {
    @Composable
    override fun Content() {

        val navigator = LocalNavigator.current

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(HorizontalPaddingSignIn),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Image(
                painter = painterResource(id = R.drawable.successfull_img),
                contentDescription = null,
                modifier = Modifier.padding(

                    vertical = Dimens.MediumPadding
                )
            )
            Button(
                onClick = {
                    navigator?.push(LoginScreen())
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        horizontal = HorizontalPaddingSignIn,
                        vertical = Dimens.MediumPadding
                    ),
                shape = RoundedCornerShape(30)
            ) {
                Text(
                    text = "Go to Login Screen",
                    Modifier.padding(vertical = Dimens.ExtraSmallPadding),
                    style = MaterialTheme.typography.displaySmall.copy(
                        fontWeight = FontWeight.Bold
                    )
                )
            }

        }
    }

}