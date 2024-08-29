package com.youppix.ecommercecourse.presentation.home_app.address

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import com.youppix.ecommercecourse.common.Resource
import com.youppix.ecommercecourse.domain.model.address.Address
import com.youppix.ecommercecourse.domain.useCases.address.AddressUseCases
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

class AddressViewModel @Inject constructor(
    private val addressUseCases: AddressUseCases,
) : ScreenModel {

    private var _state = mutableStateOf(AddressState())
    val state: State<AddressState> = _state


    fun onEvent(event: AddressEvent) {
        when (event) {
            is AddressEvent.GetAllAddress -> {
                screenModelScope.launch {
                    getAllAddress(event.userId)
                }
            }

            is AddressEvent.ToggleShowBottomSheet -> {
                _state.value = state.value.copy(
                    showBottomSheet = !state.value.showBottomSheet,
                    isInserting = event.isInserting ?: state.value.isInserting
                )
            }

            is AddressEvent.UpsertAddress -> {
                screenModelScope.launch {
                    upsertAddress(event.address)
                }
            }

            is AddressEvent.DeleteAddress -> {
                screenModelScope.launch {
                    deleteAddress(event.addressId, event.userId)
                }
            }

            is AddressEvent.ToggleSelectedAddressId -> {
                _state.value = state.value.copy(
                    selectedAddress = state.value.selectedAddress.copy(
                        addressId = event.address?.addressId ?: 0,
                        addressWilaya = event.address?.addressWilaya ?: "",
                        addressCommune = event.address?.addressCommune ?: "",
                        addressName = event.address?.addressName ?: "",
                        addressCodePostal = event.address?.addressCodePostal ?: "",
                        addressDefault = event.address?.addressDefault ?: 0
                    )
                )
            }
        }
    }

    private suspend fun upsertAddress(address: Address) {
        addressUseCases.upsertAddress(address).onEach { result ->
            when (result) {
                is Resource.Loading -> {
                    _state.value = state.value.copy(
                        isLoading = true
                    )
                }

                is Resource.Error -> {
                    _state.value = state.value.copy(
                        isLoading = false,
                        error = result.data?.message ?: result.message
                        ?: "An Unexpected Error Occurred",
                        success = false
                    )
                }

                is Resource.Successful -> {
                    _state.value = state.value.copy(
                        isLoading = false,
                        error = null,
                        success = true
                    )
                    onEvent(AddressEvent.GetAllAddress(address.userId))
                }
            }
        }.launchIn(screenModelScope)
    }

    private suspend fun deleteAddress(addressId: Int, userId: Int) {
        addressUseCases.deleteAddress(addressId = addressId, userId = userId).onEach { result ->
            when (result) {
                is Resource.Loading -> {
                    _state.value = state.value.copy(
                        isLoading = true
                    )
                }

                is Resource.Error -> {
                    _state.value = state.value.copy(
                        isLoading = false,
                        error = result.data?.message ?: result.message
                        ?: "An Unexpected Error Occurred",
                        success = false
                    )
                }

                is Resource.Successful -> {
                    _state.value = state.value.copy(
                        isLoading = false,
                        error = null,
                        success = true
                    )
                    onEvent(AddressEvent.GetAllAddress(userId))
                }
            }
        }.launchIn(screenModelScope)
    }

    private suspend fun getAllAddress(userId: Int) {
        addressUseCases.getAllAddress(userId).onEach { result ->
            when (result) {
                is Resource.Loading -> {
                    _state.value = state.value.copy(
                        isLoading = true
                    )
                }

                is Resource.Error -> {
                    _state.value = state.value.copy(
                        isLoading = false,
                        error = result.data?.message ?: result.message
                        ?: "An Unexpected Error Occurred"
                    )
                }

                is Resource.Successful -> {
                    _state.value = state.value.copy(
                        isLoading = false,
                        items = result.data?.data ?: emptyList(),
                        error = null
                    )
                }
            }
        }.launchIn(screenModelScope)
    }


}