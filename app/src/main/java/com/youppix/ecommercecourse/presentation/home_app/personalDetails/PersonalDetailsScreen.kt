package com.youppix.ecommercecourse.presentation.home_app.personalDetails

import android.app.Person
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.Phone
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.hilt.getScreenModel
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.youppix.ecommercecourse.R
import com.youppix.ecommercecourse.common.Dimens
import com.youppix.ecommercecourse.common.Dimens.BottomBarHeight
import com.youppix.ecommercecourse.common.Dimens.MediumPadding
import com.youppix.ecommercecourse.common.Dimens.SmallPadding
import com.youppix.ecommercecourse.domain.model.user.User
import com.youppix.ecommercecourse.presentation.components.CustomTextField
import com.youppix.ecommercecourse.presentation.components.CustomTopAppBar
import com.youppix.ecommercecourse.presentation.home_app.customSize.components.CustomSizeTextField
import java.util.Locale

data class PersonalDetailsScreen(val userData: User) : Screen {
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        val viewModel: PersonalDetailsViewModel = getScreenModel()
        val state by viewModel.state

        val isArabic by remember {
            mutableStateOf(Locale.getDefault().language == "ar")
        }

        LaunchedEffect(Unit) {
            viewModel.setState(userData)
        }

        Scaffold(modifier = Modifier.fillMaxSize(),
            topBar = {
                CustomTopAppBar(
                    title = stringResource(id = R.string.personalDetails), isArabic = isArabic,
                    onSaveClick = {

                    }
                ) {
                    navigator.pop()
                }
            })
        { innerPadding ->
            Column(
                modifier = Modifier
                    .padding(innerPadding)
                    .animateContentSize()
            ) {

                //User Name
                CustomTextField(
                    modifier = Modifier.padding(top = MediumPadding , bottom = SmallPadding)
                        .padding(
                            horizontal = MediumPadding
                        ),
                    value = state.user.userName,
                    label = stringResource(id = R.string.userName),
                    placeholder = stringResource(id = R.string.enterYourUserName),
                    trailingIcon = Icons.Outlined.Person,
                    onValueChange = { value ->
                        viewModel.onEvent(PersonalDetailsEvent.UpdateUserName(value))
                    },
                    isError = !state.userNameError.isNullOrEmpty(),
                    errorMessage = state.userNameError ?: ""
                )
                //Email
                CustomTextField(
                    modifier = Modifier.padding(bottom = SmallPadding).padding(
                        horizontal = MediumPadding
                    ),
                    value = state.user.userEmail,
                    label = stringResource(id = R.string.email),
                    placeholder = stringResource(id = R.string.enterYourEmail),
                    trailingIcon = Icons.Outlined.Email,
                    onValueChange = { value ->
                        viewModel.onEvent(PersonalDetailsEvent.UpdateEmail(value))
                    },
                    isError = !state.emailError.isNullOrEmpty(),
                    errorMessage = state.emailError ?: ""
                )
                //Phone

                CustomTextField(
                    modifier = Modifier.padding(bottom = SmallPadding).padding(
                        horizontal = MediumPadding
                    ),
                    value = state.user.userPhone,
                    label = stringResource(id = R.string.phone),
                    placeholder = stringResource(id = R.string.enterYourPhone),
                    trailingIcon = Icons.Outlined.Phone,
                    onValueChange = { value ->
                        viewModel.onEvent(PersonalDetailsEvent.UpdatePhone(value))
                    },
                    isError = !state.phoneError.isNullOrEmpty(),
                    isPassword = false,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
                    errorMessage = state.phoneError ?: ""
                )

                //Password

                CustomTextField(
                    modifier = Modifier.padding(bottom = SmallPadding).padding(
                        horizontal = MediumPadding
                    ),
                    value = state.oldPassword,
                    onValueChange = { value ->
                        viewModel.onEvent(PersonalDetailsEvent.UpdatePassword(value))
                    },
                    label = stringResource(id = R.string.password),
                    placeholder = stringResource(id = R.string.enterYourPassword),
                    trailingIcon = Icons.Outlined.Lock,
                    isError = !state.passwordError.isNullOrEmpty(),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                    isPassword = true,
                    showPassword = state.showPassword,
                    onShowPassword = {
                        viewModel.onEvent(PersonalDetailsEvent.ToggleShowPassword)
                    },
                    errorMessage = state.passwordError ?: ""
                )

                CustomTextField(
                    modifier = Modifier.padding(bottom =  BottomBarHeight.plus(MediumPadding)).padding(
                        horizontal = MediumPadding
                    ),
                    value = state.newPassword,
                    onValueChange = { value ->
                        viewModel.onEvent(PersonalDetailsEvent.UpdateNewPassword(value))
                    },
                    label = stringResource(id = R.string.newPassword),
                    placeholder = stringResource(id = R.string.enterYourNewPassword),
                    trailingIcon = Icons.Outlined.Lock,
                    isError = !state.newPasswordError.isNullOrEmpty(),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                    isPassword = true,
                    showPassword = state.showNewPassword,
                    onShowPassword = {
                        viewModel.onEvent(PersonalDetailsEvent.ToggleShowNewPassword)
                    },
                    errorMessage = state.passwordError ?: ""
                )


            }

        }

    }
}