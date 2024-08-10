package com.youppix.ecommercecourse.presentation.home_app.details.customSize

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.hilt.getScreenModel
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.youppix.ecommercecourse.R
import com.youppix.ecommercecourse.common.Constant.APP_ENTRY
import com.youppix.ecommercecourse.presentation.components.CustomDialog
import com.youppix.ecommercecourse.presentation.home_app.details.customSize.components.BottomBarSection
import com.youppix.ecommercecourse.presentation.home_app.details.customSize.components.CustomSizeContent
import com.youppix.ecommercecourse.presentation.home_app.details.customSize.components.TopBarSection
import java.util.Locale


class CustomSizeScreen(private val userId: Int?) : Screen {
    @Composable
    override fun Content() {

        val navigator = LocalNavigator.currentOrThrow
        val viewModel: CustomSizeViewModel = getScreenModel()
        val state by viewModel.state

        val focusRequester = remember { FocusRequester() }
        val keyboardController = LocalSoftwareKeyboardController.current
        val context = LocalContext.current

        LaunchedEffect(Unit) {
            userId?.let {
                viewModel.onEvent(CustomSizeEvent.UpdateUserId(it))
            } ?: run {
                val id = context.getSharedPreferences(APP_ENTRY, 0).getString("userId", "")
                if (!id.isNullOrEmpty())
                    viewModel.onEvent(CustomSizeEvent.UpdateUserId(id.toInt()))
            }
            focusRequester.requestFocus()
            keyboardController?.show()
        }

        LaunchedEffect(state.isSuccessful) {
            if (state.isSuccessful) {
                navigator.pop()
            }
        }

        val isArabic = Locale.getDefault().language == "ar"

        Scaffold(Modifier.fillMaxSize(),
            topBar = {
                TopBarSection(
                    customSizeName = state.customSizeName,
                    focusRequester = focusRequester,
                    isArabic = isArabic,
                    onValueChange = {
                        viewModel.onEvent(CustomSizeEvent.UpdateName(it))
                    },
                    navigateBack = {
                        navigator.pop()
                    }
                )
            },
            bottomBar = {
                BottomBarSection(state, viewModel::onEvent)
            }) { innerPadding ->

            CustomSizeContent(
                modifier = Modifier.padding(innerPadding),
                state = state,
                event = viewModel::onEvent
            )

            //Alert Dialog
            CustomDialog(
                title = stringResource(id = R.string.alert),
                message = stringResource(id = R.string.youHaveNotSetTheNameOfYourSize),
                showDialog = state.showAlertDialog,
                onConfirmRequest = {
                    viewModel.onEvent(CustomSizeEvent.HideDialog)
                    focusRequester.requestFocus()
                    keyboardController?.show()
                },
                onDismissRequest = {
                    viewModel.onEvent(CustomSizeEvent.HideDialog)
                })

            //Error Dialog
            CustomDialog(
                title = stringResource(id = R.string.errorOccurred),
                message = stringResource(id = R.string.pleaseCheckYourValuesOrYourInternetConnection),
                showDialog = state.showErrorDialog,
                onConfirmRequest = {
                    viewModel.onEvent(CustomSizeEvent.HideDialog)
                },
                onDismissRequest = {
                    viewModel.onEvent(CustomSizeEvent.HideDialog)
                })
        }
    }


}


