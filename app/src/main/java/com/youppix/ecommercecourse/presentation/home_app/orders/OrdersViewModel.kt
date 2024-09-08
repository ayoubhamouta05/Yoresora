package com.youppix.ecommercecourse.presentation.home_app.orders

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import cafe.adriel.voyager.core.model.ScreenModel
import com.youppix.ecommercecourse.domain.useCases.orders.OrdersUseCases
import javax.inject.Inject

class OrdersViewModel @Inject constructor(
private val ordersUseCases : OrdersUseCases
) : ScreenModel {

    private var _state = mutableStateOf(OrdersState(isLoading = true))
    val state : State<OrdersState> = _state

    fun onEvent(event : OrdersEvent){
        when(event){
            is OrdersEvent.UpdateOrderType -> {
                _state.value = state.value.copy(
                    ordersType = event.type
                )
            }
        }
    }

}