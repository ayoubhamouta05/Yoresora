package com.youppix.ecommercecourse.presentation.home_app.address

import android.util.Log
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
                        addressWilaya = event.address?.addressWilaya,
                        addressCommune = event.address?.addressCommune,
                        addressName = event.address?.addressName ?: "",
                        addressCodePostal = event.address?.addressCodePostal ?: "",
                        addressDefault = event.address?.addressDefault ?: 0
                    )
                )
            }

            is AddressEvent.GetCommune -> {
                screenModelScope.launch {
                    getCommune(event.wilayaId)
                }
            }

            is AddressEvent.UpdateAddressCodePostal -> {
                _state.value = state.value.copy(
                    selectedAddress = state.value.selectedAddress.copy(
                        addressCodePostal = event.codePostal
                    )
                )
            }

            AddressEvent.ToggleCommuneDropMenu -> {
                _state.value = state.value.copy(
                    dropCommuneMenu = !state.value.dropCommuneMenu
                )
            }

            AddressEvent.ToggleWilayaDropMenu -> {
                _state.value = state.value.copy(
                    dropWilayaMenu = !state.value.dropWilayaMenu
                )
            }

            is AddressEvent.SetCommune -> {
                _state.value = state.value.copy(
                    selectedAddress = state.value.selectedAddress.copy(
                        addressCommune = event.commune
                    )
                )
                onEvent(AddressEvent.ToggleCommuneDropMenu)
            }

            is AddressEvent.SetWilaya -> {
                _state.value = state.value.copy(
                    selectedAddress = state.value.selectedAddress.copy(
                        addressWilaya = event.wilaya,
                        addressCommune = null
                    ),
                    communeList = emptyList()
                )
                onEvent(AddressEvent.ToggleWilayaDropMenu)
                onEvent(AddressEvent.GetCommune(event.wilaya.wilayaId))
            }

            is AddressEvent.UpdateAddressName -> {
                _state.value = state.value.copy(
                    selectedAddress = state.value.selectedAddress.copy(
                        addressName = event.name
                    )
                )
            }

            is AddressEvent.UpdateAddressDefault -> {
                _state.value = state.value.copy(
                    selectedAddress = state.value.selectedAddress.copy(
                        addressDefault = if (event.value) 1 else 0
                    )
                )
            }

            is AddressEvent.UpdateDefaultAddressIndex -> {
                _state.value = state.value.copy(
                    defaultAddressIndex = event.index
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
                        wilayaList = result.data?.wilayas ?: emptyList(),
                        error = null
                    )
                    if (state.value.wilayaList.isNotEmpty()) {
                        onEvent(AddressEvent.SetWilaya(state.value.wilayaList[0]))
                        onEvent(AddressEvent.ToggleWilayaDropMenu)
                    }
                }
            }
            Log.d("AddressViewModel", "getAllAddress: ${result.data}")
        }.launchIn(screenModelScope)
    }


    private suspend fun getCommune(wilayaId: Int) {
        addressUseCases.getCommune(wilayaId).onEach { result ->
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
                        communeList = result.data?.data ?: emptyList(),
                        error = null
                    )
                }
            }
        }.launchIn(screenModelScope)
    }

}