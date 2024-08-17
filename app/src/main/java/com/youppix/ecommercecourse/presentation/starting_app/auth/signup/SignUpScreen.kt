package com.youppix.ecommercecourse.presentation.starting_app.auth.signup

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
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
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.Phone
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
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
import com.youppix.ecommercecourse.common.Dimens.ExtraSmallPadding
import com.youppix.ecommercecourse.common.Dimens.HorizontalPaddingSignIn
import com.youppix.ecommercecourse.common.Dimens.MediumPadding
import com.youppix.ecommercecourse.common.Dimens.SmallPadding
import com.youppix.ecommercecourse.presentation.starting_app.auth.components.CustomProgressIndicator
import com.youppix.ecommercecourse.presentation.starting_app.auth.verification.VerificationEmailSignUpScreen
import com.youppix.ecommercecourse.presentation.starting_app.auth.login.LoginScreen
import com.youppix.ecommercecourse.presentation.starting_app.auth.login.components.SocialMediaItem
import com.youppix.ecommercecourse.presentation.components.CustomTextField
import kotlinx.coroutines.launch
import java.util.Locale

class SignUpScreen : Screen {
    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    override fun Content() {
        val isArabic = LocalContext.current.getSharedPreferences(Constant.APP_LANG, 0)
            .getString(Constant.APP_LANG, Locale.getDefault().language) == "ar"

        val navigator = LocalNavigator.current

        val viewModel: SignUpViewModel = hiltViewModel()
        val state = viewModel.signUpState.value
        val context = LocalContext.current
        val scope = rememberCoroutineScope()

        // Observing the state to navigate or show toast
        LaunchedEffect(state.signUpSuccessful, state.signUpErrorMessage) {
            if (state.signUpSuccessful) {
                navigator?.push(
                    VerificationEmailSignUpScreen(state)
                )
            } else if (!state.signUpErrorMessage.isNullOrEmpty()) {
                Toast.makeText(
                    context,
                    state.signUpErrorMessage,
                    Toast.LENGTH_SHORT
                ).show()
            }
            viewModel.resetState()

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
                            text = stringResource(id = R.string.signup),
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
                                        .size(HorizontalPaddingSignIn),
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
                        .verticalScroll(state = scrollState, enabled = true),
                    verticalArrangement = Arrangement.Center
                ) {
                    Spacer(modifier = Modifier.weight(1f))
                    Text(
                        text = stringResource(id = R.string.welcomeToOurCommunity),
                        style = MaterialTheme.typography.displayMedium.copy(fontWeight = FontWeight.Bold),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(
                                top = innerPadding
                                    .calculateTopPadding(),
                                end = HorizontalPaddingSignIn,
                                start = HorizontalPaddingSignIn
                            ),
                        textAlign = TextAlign.Center
                    )
                    Text(
                        text = stringResource(id = R.string.signUpBodyText),
                        style = MaterialTheme.typography.bodyMedium,
                        color = Color.Gray,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(
                                start = HorizontalPaddingSignIn,
                                end = HorizontalPaddingSignIn
                            ),
                        textAlign = TextAlign.Center
                    )
                    //User Name
                    CustomTextField(
                        modifier = Modifier.padding(top = MediumPadding , bottom = SmallPadding)
                            .padding(
                                horizontal = Dimens.HorizontalPaddingSignIn
                            ),
                        value = state.userName,
                        label = stringResource(id = R.string.userName),
                        placeholder = stringResource(id = R.string.enterYourUserName),
                        trailingIcon = Icons.Outlined.Person,
                        onValueChange = { value ->
                            viewModel.updateUserName(value)
                        },
                        isError = !state.userNameError.isNullOrEmpty(),
                        errorMessage = state.userNameError ?: ""
                    )
                    //Email
                    CustomTextField(
                        modifier = Modifier.padding(bottom = SmallPadding)
                            .padding(
                                horizontal = Dimens.HorizontalPaddingSignIn
                            ),
                        value = state.email,
                        label = stringResource(id = R.string.email),
                        placeholder = stringResource(id = R.string.enterYourEmail),
                        trailingIcon = Icons.Outlined.Email,
                        onValueChange = { value ->
                            viewModel.updateEmail(value)
                        },
                        isError = !state.emailError.isNullOrEmpty(),
                        errorMessage = state.emailError ?: ""
                    )
                    //Phone

                    CustomTextField(
                        modifier = Modifier.padding(bottom = SmallPadding)
                            .padding(
                                horizontal = Dimens.HorizontalPaddingSignIn
                            ),
                        value = state.phone,
                        label = stringResource(id = R.string.phone),
                        placeholder = stringResource(id = R.string.enterYourPhone),
                        trailingIcon = Icons.Outlined.Phone,
                        onValueChange = { value ->
                            viewModel.updatePhone(value)
                        },
                        isError = !state.phoneError.isNullOrEmpty(),
                        isPassword = false,
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
                        errorMessage = state.phoneError ?: ""
                    )

                    //Password

                    CustomTextField(
                        modifier = Modifier.padding(bottom = MediumPadding)
                            .padding(
                                horizontal = Dimens.HorizontalPaddingSignIn
                            ),
                        value = state.password,
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
                            viewModel.showOrHidePassword(it)
                        },
                        errorMessage = state.passwordError ?: ""
                    )

                    Button(
                        onClick = {

                            if (viewModel.validateForm(
                                    context = context,
                                    userName = state.userName,
                                    email = state.email,
                                    password = state.password,
                                    phone = state.phone
                                )
                            ) {

                                scope.launch {
                                    viewModel.addUser(
                                        state.userName,
                                        state.email,
                                        state.phone,
                                        state.password
                                    )
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
                            text = stringResource(id = R.string.alreadyHaveAccount),
                            style = MaterialTheme.typography.bodyLarge.copy(
                                fontWeight = FontWeight.SemiBold
                            ),
                            color = Color.Gray,
                            modifier = Modifier.offset(x = SmallPadding)
                        )
                        TextButton(onClick = {
                            navigator?.let {
                                if (!it.pop()) {
                                    it.push(LoginScreen())
                                }
                            }
                        }) {
                            Text(
                                text = stringResource(id = R.string.signin),
                                style = MaterialTheme.typography.bodyLarge.copy(
                                    fontWeight = FontWeight.Bold
                                ),
                                color = MaterialTheme.colorScheme.primary
                            )

                        }
                    }


                }


                CustomProgressIndicator(show = state.isLoading)


            }
        }
    }
}