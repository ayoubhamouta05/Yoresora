package com.youppix.ecommercecourse.presentation.home_app.payment

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.viewModelScope
import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import com.youppix.ecommercecourse.domain.manager.NetworkConnectivityManager
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
        // todo : handle this error here
        networkConnectivityManagerUseCase().onEach {
            _state.value = state.value.copy(
                networkState = it
            )
//            _status.value = it
//            if (firstTime && status.value == NetworkConnectivityManager.Status.Available) {
//                firstTime = false
//            } else {
//                _showNetworkStatus.value = true
//            }
        }.launchIn(screenModelScope)

//        if (status.value == NetworkConnectivityManager.Status.Unavailable) {
//            _showNetworkStatus.value = true
//        }
    }

}