package com.youppix.ecommercecourse.presentation.starting_app.auth.forgotPassword.resetPassword

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import com.youppix.ecommercecourse.R
import com.youppix.ecommercecourse.common.Constant
import com.youppix.ecommercecourse.common.Dimens
import com.youppix.ecommercecourse.presentation.starting_app.auth.forgotPassword.ForgotPasswordState
import com.youppix.ecommercecourse.presentation.components.CustomTextField
import com.youppix.ecommercecourse.presentation.starting_app.auth.forgotPassword.ForgotPasswordViewModel
import kotlinx.coroutines.launch
import java.util.Locale

class ResetPasswordScreen(
    private var forgotPasswordState: ForgotPasswordState
) : Screen {
    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    override fun Content() {
        val isArabic = LocalContext.current.getSharedPreferences(Constant.APP_LANG, 0)
            .getString(Constant.APP_LANG, Locale.getDefault().language) == "ar"

        val navigator = LocalNavigator.current

        val viewModel: ForgotPasswordViewModel = hiltViewModel()
        val state = viewModel.forgotPasswordState.value

        LaunchedEffect(Unit) {
            viewModel.setState(forgotPasswordState)
        }

        val context = LocalContext.current
        val scope = rememberCoroutineScope()
        LaunchedEffect(state.resetPasswordError, state.resetPasswordSuccessful) {
            Log.d("ResetPasswordScreen", "Content: $state")
            if (state.resetPasswordSuccessful) {
                navigator?.replace(SuccessfulResetPasswordScreen())
                viewModel.resetState()
            } else if (state.resetPasswordError != null) {
                Toast.makeText(context, state.resetPasswordError, Toast.LENGTH_SHORT).show()
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
                                text = stringResource(id = R.string.forgotPassword),
                                modifier = Modifier,
                                textAlign = TextAlign.Center,
                                style = MaterialTheme.typography.titleSmall.copy(fontSize = 24.sp),
                                color = Color.Gray
                            )
                        },
                        navigationIcon = {
                            IconButton(
                                onClick = {
                                    navigator?.pop()
                                },
                            ) {
                                Image(
                                    Icons.Default.KeyboardArrowDown,
                                    colorFilter = ColorFilter.tint(color = Color.Gray),
                                    modifier = Modifier
                                        .rotate(if (isArabic) 90f else -90f)
                                        .size(Dimens.HorizontalPaddingSignIn),
                                    contentDescription = null,
                                    contentScale = ContentScale.Fit
                                )
                            }
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
                        .fillMaxSize()
                        .verticalScroll(state = scrollState, enabled = true)
                ) {

                    Text(
                        text = stringResource(id = R.string.resetPassword),
                        style = MaterialTheme.typography.displayMedium.copy(fontWeight = FontWeight.Bold),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(
                                top = innerPadding
                                    .calculateTopPadding()
                                    .plus(Dimens.HorizontalPaddingSignIn),
                                end = Dimens.HorizontalPaddingSignIn,
                                start = Dimens.HorizontalPaddingSignIn
                            ),
                        textAlign = TextAlign.Center
                    )
                    Text(
                        text = stringResource(id = R.string.resetPasswordBodyText),
                        style = MaterialTheme.typography.bodyMedium,
                        color = Color.Gray,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(
                                start = Dimens.HorizontalPaddingSignIn,
                                end = Dimens.HorizontalPaddingSignIn
                            ),
                        textAlign = TextAlign.Center
                    )

                    CustomTextField(
                        modifier = Modifier.padding(top = Dimens.MediumPadding)
                            .padding(
                                horizontal = Dimens.HorizontalPaddingSignIn
                            ),
                        value = state.newPassword,
                        onValueChange = { value ->
                            viewModel.updatePassword(value)
                        },
                        label = stringResource(id = R.string.password),
                        placeholder = stringResource(id = R.string.enterYourPassword),
                        trailingIcon = Icons.Outlined.Lock,
                        isError = !state.passwordError.isNullOrEmpty(),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                        isPassword = true,
                        showPassword = state.showPassword,
                        onShowPassword = {
                            viewModel.showPassword(it)
                        },
                        errorMessage = state.passwordError ?: ""
                    )

                    Button(
                        onClick = {
                            if (viewModel.checkPassword(
                                    password = state.newPassword,
                                    context = context
                                )
                            ) {
                                scope.launch {
                                    viewModel.resetPassword(state.email, state.newPassword)
                                }
                            }

                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(
                                horizontal = Dimens.HorizontalPaddingSignIn,
                                vertical = Dimens.MediumPadding
                            ),
                        shape = RoundedCornerShape(30)
                    ) {
                        Text(
                            text = stringResource(id = R.string.continuee),
                            Modifier.padding(vertical = Dimens.ExtraSmallPadding),
                            style = MaterialTheme.typography.displaySmall.copy(
                                fontWeight = FontWeight.Bold
                            )
                        )
                    }

                }
            }
        }
    }
}