package com.youppix.ecommercecourse.presentation.home_app.address

import android.content.Context
import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import com.youppix.ecommercecourse.R
import com.youppix.ecommercecourse.common.Resource
import com.youppix.ecommercecourse.data.remote.auth.dto.AuthResponse
import com.youppix.ecommercecourse.domain.model.address.Address
import com.youppix.ecommercecourse.domain.useCases.address.AddressUseCases
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.distinctUntilChangedBy
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

class AddressViewModel @Inject constructor(
    private val addressUseCases: AddressUseCases,
) : ScreenModel {

    private var _state = mutableStateOf(AddressState(isLoading = true))
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
                Log.d("AddressViewModel", "check error: ${event.checkError}")
                if (event.checkError) {
                    if (checkInformationError(event.address, context = event.context)) {
                        _state.value = state.value.copy(
                            addressNameError = null,
                            specificAddressError = null
                        )
                        onEvent(AddressEvent.ToggleShowBottomSheet())
                        screenModelScope.launch {
                            upsertAddress(event.address, event.userCustomerId, event.isArabic)
                        }
                    }
                } else {
                    screenModelScope.launch {
                        upsertAddress(
                            event.address,
                            event.userCustomerId,
                            event.isArabic,
                            isUpdatingDefaultAddress = true
                        )
                    }
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
                        addressDefault = event.address?.addressDefault ?: 0,
                        addressSpecific = event.address?.addressSpecific ?: ""
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
                        addressWilaya = event.wilaya, addressCommune = null
                    ), communeList = emptyList()
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

            is AddressEvent.UpdateSpecificAddress -> {
                _state.value = state.value.copy(
                    selectedAddress = state.value.selectedAddress.copy(
                        addressSpecific = event.value
                    )
                )
            }

            is AddressEvent.UpdateSelectAddressSuccess -> {
                _state.value = state.value.copy(
                    selectAddressSuccess = event.value
                )
            }

            is AddressEvent.UpsertMultipleAddress -> {
                if (event.checkError) {
                    if (checkInformationError(event.address2, context = event.context)) {
                        _state.value = state.value.copy(
                            addressNameError = null,
                            specificAddressError = null
                        )
                        onEvent(AddressEvent.ToggleShowBottomSheet())
                        screenModelScope.launch {
                            upsertMultipleAddress(event.address1,
                                event.address2,
                                event.userCustomerId,
                                event.isArabic)
                        }
                    }
                } else {
                    screenModelScope.launch {
                        upsertMultipleAddress(
                            event.address1,
                            event.address2,
                            event.userCustomerId,
                            event.isArabic,
                            isUpdatingDefaultAddress = true
                        )
                    }
                }
            }
        }
    }

    private suspend fun upsertAddress(
        address: Address,
        userCustomerId: String,
        isArabic: Boolean,
        isUpdatingDefaultAddress: Boolean = false,
    ) {
        addressUseCases.upsertAddress(address, userCustomerId, isArabic).onEach { result ->
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
                    Log.d(
                        "AddressViewModel",
                        "upsertAddress: stop loading , ${address.addressDefault}"
                    )
                    _state.value = state.value.copy(
                        isLoading = false,
                        error = null,
                        success = true,
                        selectAddressSuccess = if (address.addressDefault == 0) state.value.selectAddressSuccess
                        else
                            isUpdatingDefaultAddress
                    )
                    onEvent(AddressEvent.GetAllAddress(address.userId))
                }
            }
            Log.d("AddressViewModel", "result upsert: ${result.data}")
        }.launchIn(screenModelScope)
    }

    private suspend fun upsertMultipleAddress(
        address1: Address,
        address2: Address,
        userCustomerId: String,
        isArabic: Boolean,
        isUpdatingDefaultAddress: Boolean = false,
    ) {
        combine(
            addressUseCases.upsertAddress(address1, userCustomerId, isArabic),
            addressUseCases.upsertAddress(address2, userCustomerId, isArabic)
        ) { result1, result2 ->
            when (result1) {
                is Resource.Loading -> {
                    _state.value = state.value.copy(
                        isLoading = true
                    )
                }

                else -> {}
            }
            when (result2) {
                is Resource.Loading -> {
                    _state.value = state.value.copy(
                        isLoading = true
                    )
                }

                is Resource.Error -> {
                    _state.value = state.value.copy(
                        isLoading = false,
                        error = result2.data?.message ?: result2.message
                        ?: "An Unexpected Error Occurred",
                        success = false
                    )
                }

                is Resource.Successful -> {
                    _state.value = state.value.copy(
                        isLoading = false,
                        error = null,
                        success = true,
                        selectAddressSuccess = isUpdatingDefaultAddress
                    )
                    if (!isUpdatingDefaultAddress) {
                        onEvent(AddressEvent.GetAllAddress(address2.userId))
                    }
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
                        isLoading = false, error = null, success = true
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
                }
            }
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

    private fun checkInformationError(address: Address, context: Context): Boolean {
        return if (address.addressName.isEmpty() || address.addressName.isBlank()) {
            _state.value = state.value.copy(
                addressNameError = context.getString(R.string.addressNameEmptyErrorMsg)
            )
            false
        } else if (address.addressWilaya == null) {
            _state.value = state.value.copy(
                addressNameError = null,
                wilayaError = context.getString(R.string.pleaseSelectWilayaErrorMsg)
            )
            false
        } else if (address.addressCommune == null) {
            _state.value = state.value.copy(
                addressNameError = null,
                wilayaError = null,
                communeError = context.getString(R.string.pleaseSelectCommuneErrorMsg)
            )
            false
        } else if (address.addressCodePostal.isEmpty() || address.addressCodePostal.isBlank()) {
            _state.value = state.value.copy(
                addressNameError = null,
                wilayaError = null,
                communeError = null,
                codePostalError = context.getString(R.string.codePostalEmptyErrorMsg)
            )
            false
        } else if (address.addressCodePostal.length != 5) {
            _state.value = state.value.copy(
                addressNameError = null,
                wilayaError = null,
                communeError = null,
                codePostalError = context.getString(R.string.codePostalWrongErrorMsg)
            )
            false
        } else if (state.value.selectedAddress.addressSpecific.isEmpty() || state.value.selectedAddress.addressSpecific.isBlank()) {
            _state.value = state.value.copy(
                addressNameError = null,
                wilayaError = null,
                communeError = null,
                codePostalError = null,
                specificAddressError = context.getString(R.string.specificAddressEmptyErrorMsg)
            )
            false
        } else {
            true
        }
    }

}