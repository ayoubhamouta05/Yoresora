package com.youppix.ecommercecourse.presentation.home_app.details.customSize

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import com.youppix.ecommercecourse.common.Resource
import com.youppix.ecommercecourse.domain.model.details.CustomSize
import com.youppix.ecommercecourse.domain.repository.details.DetailsRepository
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

class CustomSizeViewModel @Inject constructor(
    private val detailsRepository: DetailsRepository
) : ScreenModel {

    private var _state = mutableStateOf(CustomSizeState())
    val state: State<CustomSizeState> = _state


    fun onEvent(event: CustomSizeEvent) {
        when (event) {
            is CustomSizeEvent.UpdateShoulderWidth -> {
                _state.value = state.value.copy(
                    shoulderWidth = event.value
                )
            }

            is CustomSizeEvent.UpdateChestCircumference -> {
                _state.value = state.value.copy(
                    chestCircumference = event.value
                )
            }

            is CustomSizeEvent.UpdateChestHeight -> {
                _state.value = state.value.copy(
                    chestHeight = event.value
                )
            }

            is CustomSizeEvent.UpdateWaistLine -> {
                _state.value = state.value.copy(
                    waistLine = event.value
                )
            }

            is CustomSizeEvent.UpdateButtocksCircumference -> {
                _state.value = state.value.copy(
                    buttocksCircumference = event.value
                )
            }

            is CustomSizeEvent.UpdateButtocksHeight -> {
                _state.value = state.value.copy(
                    buttocksHeight = event.value
                )
            }

            is CustomSizeEvent.UpdateArmCircumference -> {
                _state.value = state.value.copy(
                    armCircumference = event.value
                )
            }

            is CustomSizeEvent.UpdateWristCircumference -> {
                _state.value = state.value.copy(
                    wristCircumference = event.value
                )
            }

            is CustomSizeEvent.UpdateDesiredArmLength -> {
                _state.value = state.value.copy(
                    desiredArmLength = event.value
                )
            }

            is CustomSizeEvent.UpdateTotalLength -> {
                _state.value = state.value.copy(
                    totalLength = event.value
                )
            }

            is CustomSizeEvent.UpdateUserId -> {
                _state.value = state.value.copy(
                    userId = event.value
                )
            }

            is CustomSizeEvent.UpdateName -> {
                _state.value = state.value.copy(
                    customSizeName = event.value
                )
            }

            is CustomSizeEvent.OnConfirm -> {
                event.value.apply {
                    saveCustomSize(
                        CustomSize(
                            userId = userId,
                            customSizeName = customSizeName,
                            shoulderWidth = shoulderWidth,
                            chestCircumference = chestCircumference,
                            chestHeight = chestHeight,
                            waistLine = waistLine,
                            buttocksCircumference = buttocksCircumference,
                            buttocksHeight = buttocksHeight,
                            armCircumference = armCircumference,
                            wristCircumference = wristCircumference,
                            desiredArmLength = desiredArmLength,
                            totalLength = totalLength
                        )
                    )
                }

            }

            is CustomSizeEvent.HideDialog -> {
                _state.value = state.value.copy(
                    showAlertDialog = false,
                    showErrorDialog = false
                )
            }

            is CustomSizeEvent.OnReset -> {
                _state.value = CustomSizeState(
                    userId = _state.value.userId
                )
            }
        }
    }

    private fun saveCustomSize(customSize: CustomSize) {

        if (state.value.customSizeName == CustomSizeState().customSizeName) {
            _state.value = state.value.copy(
                showAlertDialog = true
            )
        } else if (isHaveError(state.value)) {
            _state.value = state.value.copy(
                showErrorDialog = true
            )
        } else {

            screenModelScope.launch {
                detailsRepository.upsertCustomSize(customSize).onEach { result ->
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
                                isSuccessful = false
                            )
                        }

                        is Resource.Successful -> {
                            _state.value = state.value.copy(
                                isLoading = false,
                                isSuccessful = true,
                                showErrorDialog = false
                            )
                        }
                    }
                    Log.d("CustomSizeVIewModel", "result  : ${result.data}")
                }.launchIn(screenModelScope)
            }


        }


    }

    private fun isHaveError(value: CustomSizeState): Boolean {
        val isChanged = value.copy(
            userId = state.value.userId,
            customSizeName = state.value.customSizeName,
            showAlertDialog = false,
            showErrorDialog = false
        ) != CustomSizeState(
            userId = state.value.userId,
            customSizeName = state.value.customSizeName,
            showAlertDialog = false,
            showErrorDialog = false
        )
        return !isChanged
    }


}