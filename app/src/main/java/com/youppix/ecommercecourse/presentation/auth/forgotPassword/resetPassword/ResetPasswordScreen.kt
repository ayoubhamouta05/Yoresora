package com.youppix.ecommercecourse.presentation.auth.forgotPassword.resetPassword

import androidx.compose.foundation.Image
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
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
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
import com.youppix.ecommercecourse.presentation.components.CustomTextField
import com.youppix.ecommercecourse.presentation.auth.forgotPassword.ForgotPasswordViewModel
import java.util.Locale

class ResetPasswordScreen : Screen {
    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    override fun Content() {
        val isEnglish = LocalContext.current.getSharedPreferences(Constant.APP_LANG, 0)
            .getString(Constant.APP_LANG, Locale.getDefault().language) == "en"

        val navigator = LocalNavigator.current

        val viewModel: ForgotPasswordViewModel = hiltViewModel()

        CompositionLocalProvider(
            if (isEnglish) {
                LocalLayoutDirection provides LayoutDirection.Ltr
            } else {
                LocalLayoutDirection provides LayoutDirection.Rtl
            }
        ) {
            Scaffold(modifier = Modifier.fillMaxSize(),
                topBar = {
                    CenterAlignedTopAppBar(title = {
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
                                        .rotate(if (isEnglish) 90f else -90f)
                                        .size(Dimens.HorizontalPaddingSignIn),
                                    contentDescription = null,
                                    contentScale = ContentScale.Fit
                                )
                            }
                        })

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
                        modifier = Modifier.padding(top = Dimens.MediumPadding),
                        value = viewModel.password.value,
                        onValueChange = { value ->
                            viewModel.updatePassword(value)
                        },
                        label = stringResource(id = R.string.password),
                        placeholder = stringResource(id = R.string.enterYourPassword),
                        trailingIcon = Icons.Outlined.Lock,
                        isError = viewModel.passwordError.value.isNotEmpty(),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                        isPassword = true,
                        showPassword = viewModel.showPassword.value,
                        onShowPassword = {
                            viewModel.showOrHidePassword(it)
                        },
                        errorMessage = viewModel.passwordError.value
                    )

                    val passwordEmptyErrorMsg = stringResource(id = R.string.passwordEmptyErrorMsg)
                    val passwordWrongPatternErrorMsg =
                        stringResource(id = R.string.passwordWrongPatternErrorMsg)

                    Button(
                        onClick = {
                            if(viewModel.validatePassword(
                                emptyErrorMsg = passwordEmptyErrorMsg,
                                wrongPatternErrorMsg = passwordWrongPatternErrorMsg
                            ))
                                navigator?.push(SuccessfulResetPasswordScreen())

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