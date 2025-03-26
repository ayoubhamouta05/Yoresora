package com.youppix.ecommercecourse.presentation.user_home_app.payment

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import com.youppix.ecommercecourse.domain.useCases.networkConnectivity.NetworkConnectivityManagerUseCase
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

class PaymentViewModel @Inject constructor(
    private val networkConnectivityManagerUseCase: NetworkConnectivityManagerUseCase
): ScreenModel {

    private var _state = mutableStateOf(PaymentState(isLoading = true))
    val state : State<PaymentState> = _state

    init {
        handleNetworkConnectivity()
    }
    fun onEvent(event : PaymentEvent){
        when(event){
            is PaymentEvent.ToggleLoading -> {
                _state.value = state.value.copy(
                    isLoading = event.value
                )
            }

            is PaymentEvent.ToggleShowSnackBar -> {
                _state.value = state.value.copy(
                    showSnackBar = event.value
                )
            }

            PaymentEvent.HandleNetworkState -> {
                handleNetworkConnectivity()
            }
            is PaymentEvent.ToggleNetworkState -> {
                _state.value = state.value.copy(
                    networkState = event.value
                )
            }
        }

    }

    private fun handleNetworkConnectivity() {
        networkConnectivityManagerUseCase().onEach {
            _state.value = state.value.copy(
                networkState = it
            )
        }.launchIn(screenModelScope)
    }

}