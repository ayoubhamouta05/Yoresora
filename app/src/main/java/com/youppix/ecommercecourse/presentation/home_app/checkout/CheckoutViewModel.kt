package com.youppix.ecommercecourse.presentation.home_app.checkout

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import cafe.adriel.voyager.core.model.ScreenModel
import javax.inject.Inject

class CheckoutViewModel @Inject constructor(

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
        }
    }
}