package com.youppix.ecommercecourse.presentation.home_app.checkout

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import com.youppix.ecommercecourse.common.Resource
import com.youppix.ecommercecourse.domain.useCases.checkout.CheckoutUseCases
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

class CheckoutViewModel @Inject constructor(
private val checkoutUseCases: CheckoutUseCases
) : ScreenModel {

    private var _state = mutableStateOf(CheckoutState(true))
    val state: State<CheckoutState> = _state


    fun onEvent(event: CheckoutEvent) {
        when (event) {
            is CheckoutEvent.UpdateDeliveryMethod -> {
                _state.value = state.value.copy(
                    isHomeDelivery = event.value
                )
            }

            is CheckoutEvent.GetAddress -> {
                screenModelScope.launch {
                    getAddress(event.userId)
                }
            }
        }
    }

    private suspend fun getAddress(userId : Int){
        checkoutUseCases.getAddress(userId).onEach { result->
            when(result){
                is Resource.Loading -> {
                    _state.value = state.value.copy(
                        isLoading = true
                    )
                }
                is Resource.Error -> {
                    _state.value = state.value.copy(
                        isLoading = true,
                        error = result.data?.message ?: result.message ?:"An Unexpected Error Occurred"
                    )
                }
                is Resource.Successful -> {
                    _state.value = state.value.copy(
                        isLoading = true ,
                        address = result.data?.data
                    )
                }
            }

        }.launchIn(screenModelScope)
    }

}