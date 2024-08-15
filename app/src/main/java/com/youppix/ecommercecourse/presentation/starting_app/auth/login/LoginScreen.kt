package com.youppix.ecommercecourse.presentation.starting_app.auth.login

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import com.youppix.ecommercecourse.R
import com.youppix.ecommercecourse.common.Constant.APP_ENTRY
import com.youppix.ecommercecourse.presentation.components.CustomDialog
import com.youppix.ecommercecourse.common.Dimens.ExtraSmallPadding
import com.youppix.ecommercecourse.common.Dimens.HorizontalPaddingSignIn
import com.youppix.ecommercecourse.common.Dimens.MediumPadding
import com.youppix.ecommercecourse.common.Dimens.SmallPadding
import com.youppix.ecommercecourse.presentation.home_app.MainActivity
import com.youppix.ecommercecourse.presentation.starting_app.auth.forgotPassword.CheckEmailValidationScreen
import com.youppix.ecommercecourse.presentation.starting_app.auth.login.components.SocialMediaItem
import com.youppix.ecommercecourse.presentation.starting_app.auth.signup.SignUpScreen
import com.youppix.ecommercecourse.presentation.starting_app.auth.signup.SignUpState
import com.youppix.ecommercecourse.presentation.starting_app.auth.verification.VerificationEmailSignUpScreen
import com.youppix.ecommercecourse.presentation.components.CustomTextField
import com.youppix.ecommercecourse.presentation.ui.theme.EcommerceCourseTheme
import kotlinx.coroutines.launch
import java.util.Locale

class LoginScreen() : Screen {

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    override fun Content() {

        val isArabic = Locale.getDefault().language == "ar"

        val navigator = LocalNavigator.current

        val viewModel: LoginViewModel = hiltViewModel()
        val loginState = viewModel.loginState.value

        val context = LocalContext.current
        val scope = rememberCoroutineScope()


        LaunchedEffect(
            loginState.loginSuccessful,
            loginState.needUserApprove,
            loginState.loginError
        ) {
            if (loginState.loginSuccessful) {
                loginSuccessful(viewModel, context)
                viewModel.resetState()
            } else if (!loginState.loginError.isNullOrEmpty()) {
                Toast.makeText(context, loginState.loginError, Toast.LENGTH_SHORT).show()
                viewModel.resetState()
            }

        }

        CompositionLocalProvider(
            if (!isArabic) {
                LocalLayoutDirection provides LayoutDirection.Ltr
            } else {
                LocalLayoutDirection provides LayoutDirection.Rtl
            }
        ) {
            Scaffold(modifier = Modifier.fillMaxSize(),
                topBar = {
                    CenterAlignedTopAppBar(
                        colors = TopAppBarDefaults.topAppBarColors(
                            containerColor = MaterialTheme.colorScheme.background
                        ),
                        title = {
                            Text(
                                text = stringResource(id = R.string.signin),
                                textAlign = TextAlign.Center,
                                style = MaterialTheme.typography.titleSmall.copy(fontSize = 24.sp),
                                color = Color.Gray
                            )
                        },
                        modifier = Modifier.background(MaterialTheme.colorScheme.primary)
                    )
                }) { innerPadding ->
                val scrollState = rememberScrollState()

                Column(
                    modifier = Modifier
                        .padding(
                            bottom = innerPadding.calculateBottomPadding()
                        )
                        .verticalScroll(scrollState),
                    verticalArrangement = Arrangement.Center
                ) {
                    Spacer(modifier = Modifier.weight(1f))
                    Text(
                        text = stringResource(id = R.string.welcomeBack),
                        style = MaterialTheme.typography.displayMedium.copy(fontWeight = FontWeight.Bold),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(
                                top = innerPadding
                                    .calculateTopPadding()
                                    .plus(
                                        HorizontalPaddingSignIn
                                    )
                            ),
                        textAlign = TextAlign.Center
                    )
                    Text(
                        text = stringResource(id = R.string.signInBodyText),
                        style = MaterialTheme.typography.bodyMedium,
                        color = Color.Gray,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(
                                bottom = HorizontalPaddingSignIn,
                                start = HorizontalPaddingSignIn,
                                end = HorizontalPaddingSignIn
                            ),
                        textAlign = TextAlign.Center
                    )
                    //Email
                    CustomTextField(
                        modifier = Modifier.padding(bottom = SmallPadding),
                        value = loginState.email,
                        label = stringResource(id = R.string.email),
                        placeholder = stringResource(id = R.string.enterYourEmail),
                        trailingIcon = Icons.Outlined.Email,
                        onValueChange = { value ->
                            viewModel.updateEmail(value)
                        },
                        isError = !loginState.emailError.isNullOrEmpty(),
                        isPassword = false,
                        errorMessage = loginState.emailError ?: ""
                    )

                    //Password

                    CustomTextField(
                        modifier = Modifier.padding(bottom = SmallPadding),
                        value = loginState.password,
                        onValueChange = { value ->
                            viewModel.updatePassword(value)
                        },
                        label = stringResource(id = R.string.password),
                        placeholder = stringResource(id = R.string.enterYourPassword),
                        trailingIcon = Icons.Outlined.Lock,
                        isError = !loginState.passwordError.isNullOrEmpty(),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                        isPassword = true,
                        showPassword = loginState.showPassword,
                        onShowPassword = {
                            viewModel.showPassword(it)
                        },
                        errorMessage = loginState.passwordError ?: ""
                    )

                    Row(
                        Modifier
                            .fillMaxWidth()
                            .padding(
                                horizontal = HorizontalPaddingSignIn
                            ),
                        verticalAlignment = Alignment.CenterVertically
                    ) {

                        Checkbox(checked = loginState.rememberMe, onCheckedChange = {
                            viewModel.updateRememberMe(it)
                        })

                        Text(
                            text = stringResource(id = R.string.rememberMe),
                            style = MaterialTheme.typography.bodyMedium,
                            modifier = Modifier.offset(-ExtraSmallPadding)
                        )

                        Spacer(modifier = Modifier.weight(1f))

                        TextButton(onClick = {
                            Log.d("LoginScreen", navigator.toString())
                            navigator?.push(CheckEmailValidationScreen())
                        }) {
                            Text(
                                text = stringResource(id = R.string.forgotPassword),
                                color = Color.Gray,
                                textDecoration = TextDecoration.Underline,
                                style = MaterialTheme.typography.bodyMedium
                            )
                        }
                    }


                    Button(
                        onClick = {
                            if (viewModel.validateForm(
                                    loginState.email,
                                    loginState.password,
                                    context
                                )
                            ) {
                                scope.launch {
                                    viewModel.login(loginState.email, loginState.password)
                                }
                            }
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(
                                horizontal = HorizontalPaddingSignIn,
                                vertical = MediumPadding
                            ),
                        shape = RoundedCornerShape(30)
                    ) {
                        Text(
                            text = stringResource(id = R.string.continuee),
                            Modifier.padding(vertical = ExtraSmallPadding),
                            style = MaterialTheme.typography.displaySmall.copy(
                                fontWeight = FontWeight.Bold
                            )
                        )
                    }
                    Spacer(modifier = Modifier.weight(1f))

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = HorizontalPaddingSignIn),
                        horizontalArrangement = Arrangement.Center
                    ) {

                        SocialMediaItem(image = R.drawable.ic_google) {

                        }

                        SocialMediaItem(
                            image = R.drawable.ic_facebook,
                            modifier = Modifier.padding(horizontal = ExtraSmallPadding)
                        ) {

                        }

                        SocialMediaItem(image = R.drawable.ic_twitter) {

                        }
                    }

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(
                                bottom = HorizontalPaddingSignIn,
                                top = SmallPadding
                            ),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = stringResource(id = R.string.dontHaveAnAccount),
                            style = MaterialTheme.typography.bodyLarge.copy(
                                fontWeight = FontWeight.SemiBold
                            ),
                            color = Color.Gray,
                            modifier = Modifier.offset(x = SmallPadding)
                        )
                        TextButton(onClick = {
                            navigator?.push(SignUpScreen())
                        }) {
                            Text(
                                text = stringResource(id = R.string.signup),
                                style = MaterialTheme.typography.bodyLarge.copy(
                                    fontWeight = FontWeight.Bold
                                ),
                                color = Color.Black
                            )

                        }
                    }


                }

                CustomDialog(
                    title = stringResource(id = R.string.verifyEmail),
                    message = stringResource(id = R.string.verifyEmailBody),
                    showDialog = loginState.needUserApprove ?: false,
                    onConfirmRequest = {
                        navigator?.push(VerificationEmailSignUpScreen(SignUpState(email = loginState.email)))
                        viewModel.resetState()
                    },
                    onDismissRequest = {
                        viewModel.resetState()
                    })

            }
        }
    }

    private fun loginSuccessful(viewModel: LoginViewModel, context: Context) {
        viewModel.apply {

            if (viewModel.loginState.value.rememberMe) {
                // save user login state
                saveAppEntry(APP_ENTRY, "2")

            }
            // save user Information
            saveUserInformation()
        }
        context.startActivity(Intent(context, MainActivity::class.java))
        (context as Activity).finish()

    }
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun LoginScreenPreview() {
    EcommerceCourseTheme {
        LoginScreen().Content()
    }
}