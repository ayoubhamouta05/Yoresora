package com.youppix.ecommercecourse.presentation.home_app.details.customSize

import android.content.Context
import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.res.stringResource
import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import com.youppix.ecommercecourse.R
import com.youppix.ecommercecourse.common.Resource
import com.youppix.ecommercecourse.domain.model.details.Size
import com.youppix.ecommercecourse.domain.useCases.details.DetailsUseCases
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

class CustomSizeViewModel @Inject constructor(
    private val detailsUseCases: DetailsUseCases
) : ScreenModel {

    private var _state = mutableStateOf(CustomSizeState())
    val state: State<CustomSizeState> = _state

    fun onEvent(event: CustomSizeEvent) {
        when (event) {

            is CustomSizeEvent.SetInitialSize -> {
                _state.value = state.value.copy(
                    initialSize = event.size
                )
            }

            is CustomSizeEvent.UpdateSizeInformation -> {
                _state.value = state.value.copy(
                    size = event.size.copy(
                        sizeName = event.size.sizeName
                    )
                )
            }

            is CustomSizeEvent.UpdateErrorsState -> {
                _state.value = state.value.copy(
                    errors = event.errors
                )
            }

            is CustomSizeEvent.CheckInput -> {
                checkInput(
                    context = event.context,
                    value = event.value,
                    min = event.min,
                    max = event.max,
                    name = event.name,
                    changeErrorValue = event.changeErrorValue
                )
            }

            is CustomSizeEvent.UpdateErrorMessage -> {
                _state.value = state.value.copy(
                    errorMessage = event.error
                )
            }

            is CustomSizeEvent.OnConfirm -> {
                saveCustomSize(
                    size = event.value.size,
                    context = event.context
                )
            }

            is CustomSizeEvent.HideDialog -> {
                _state.value = state.value.copy(
                    showNameErrorDialog = false,
                    showErrorDialog = false,
                    errors = state.value.errors.copy(sizeName = null)
                )
            }

            is CustomSizeEvent.OnReset -> {
                _state.value = CustomSizeState(
                    size = _state.value.size.copy(userId = state.value.size.userId)
                )
            }
        }
    }

    private fun saveCustomSize(size: Size, context: Context) {

        if (!checkSizeName(state.value.size.sizeName, state.value.errors, context)) {
            _state.value = state.value.copy(
                showNameErrorDialog = true,
                errorMessage = state.value.errorMessage
            )
        } else if (haveError(state.value.errors)) {
            _state.value = state.value.copy(
                showErrorDialog = true,
            )
        } else if (!isChanged()) {
            onEvent(CustomSizeEvent.UpdateErrorMessage(context.getString(R.string.youHaventMadeAnyModifications)))
            _state.value = state.value.copy(
                showErrorDialog = true,
            )
        } else {
            screenModelScope.launch {
                detailsUseCases.upsertCustomSize(
                    size.copy(
                        sizeName = size.sizeName.trimStart().trimEnd()
                    )
                ).onEach { result ->
                    when (result) {
                        is Resource.Loading -> {
                            _state.value = state.value.copy(
                                isLoading = true
                            )
                        }

                        is Resource.Error -> {
                            _state.value = state.value.copy(
                                isLoading = false,
                                showErrorDialog = true,
                                isSuccessful = false,
                                errorMessage = result.message ?: ""
                            )
                        }

                        is Resource.Successful -> {
                            _state.value = state.value.copy(
                                isLoading = false,
                                isSuccessful = true,
                                showErrorDialog = false,
                                errorMessage = null
                            )
                        }
                    }
                }.launchIn(screenModelScope)
            }


        }


    }

    private fun haveError(errors: CustomSizeErrorsState): Boolean {
        errors.apply {

            val haveError = !(shoulderWidth.isNullOrEmpty() &&
                    chestCircumference.isNullOrEmpty() &&
                    chestHeight.isNullOrEmpty() &&
                    waistLine.isNullOrEmpty() &&
                    buttocksCircumference.isNullOrEmpty() &&
                    buttocksHeight.isNullOrEmpty() &&
                    armCircumference.isNullOrEmpty() &&
                    wristCircumference.isNullOrEmpty() &&
                    desiredArmLength.isNullOrEmpty())

            return haveError
        }


    }

    private fun isChanged(): Boolean {
//        return state.value.size != state.value.initialSize

        state.value.apply {
        return !( size.sizeName == initialSize.sizeName &&
            size.shoulderWidth.toFloat() == initialSize.shoulderWidth.toFloat() &&
            size.chestCircumference.toFloat() == initialSize.chestCircumference.toFloat() &&
            size.chestHeight.toFloat() == initialSize.chestHeight.toFloat() &&
            size.waistLine.toFloat() == initialSize.waistLine.toFloat() &&
            size.buttocksCircumference.toFloat() == initialSize.buttocksCircumference.toFloat() &&
            size.buttocksHeight.toFloat() == initialSize.buttocksHeight.toFloat() &&
            size.armCircumference.toFloat() == initialSize.armCircumference.toFloat() &&
            size.wristCircumference.toFloat() == initialSize.wristCircumference.toFloat() &&
            size.desiredArmLength.toFloat() == initialSize.desiredArmLength.toFloat() )
        }
    }

    private fun checkSizeName(
        value: String,
        errorsState: CustomSizeErrorsState,
        context: Context
    ): Boolean {
        return if (value.isEmpty()) {
            onEvent(
                CustomSizeEvent.UpdateErrorsState(
                    errorsState.copy(
                        sizeName = context.getString(
                            R.string.sizeNameCannotBeEmpty
                        )
                    )
                )
            )
            onEvent(CustomSizeEvent.UpdateErrorMessage(state.value.errors.sizeName))
            false
        } else {
            onEvent(CustomSizeEvent.UpdateErrorsState(errorsState.copy(sizeName = null)))
            true
        }

    }

    private fun checkIfNumber(values: String): Boolean {

        try {
            if (values.count { it == '.' } > 1) {
                return false
            }
            values.toFloat()
            return true
        } catch (e: Exception) {
            return false
        }
    }

    private fun checkInput(
        context: Context,
        name : String ,
        value: String,
        min: Float,
        max: Float,
        changeErrorValue: (String?) -> Unit
    ) {
        if (value.isEmpty()) {
            changeErrorValue(context.getString(R.string.sizeCannotBeEmpty))
        } else if (checkIfNumber(value)) {
            if (value.toFloat() in min..max) {
                changeErrorValue(null)
            } else {
                changeErrorValue(
                    context.getString(
                        R.string.checkValueRangeErrorMessage,
                        max.toInt(),
                        min.toInt(),
                        name
                    )
                )
            }
        } else {
            changeErrorValue(context.getString(R.string.shouldBeNumber))
        }
    }


}