package com.youppix.ecommercecourse.presentation.user_home_app.ordersDetails

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import com.youppix.ecommercecourse.common.Resource
import com.youppix.ecommercecourse.domain.useCases.orders.OrdersUseCases
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

class OrderDetailsViewModel@Inject constructor(
    private val ordersUseCases: OrdersUseCases
) : ScreenModel {

    private var _state = mutableStateOf(OrderDetailsState(true))
    val state: State <OrderDetailsState> = _state

    fun onEvent(event: OrderDetailsEvent){
        when(event){
            is OrderDetailsEvent.GetOrderDetails -> {
                screenModelScope.launch {
                    getOrderDetails(event.orderId)
                }
            }
        }
    }

    private suspend fun getOrderDetails(orderId: String) {
        ordersUseCases.getOrderDetails(orderId).onEach { result ->
            when (result) {
                is Resource.Loading -> {
                    _state.value = state.value.copy(
                        isLoading = true
                    )
                }

                is Resource.Error -> {
                    _state.value = state.value.copy(
                        isLoading = false,
                        error = result.message ?: "An unexpected error occured"
                    )
                }

                is Resource.Successful -> {
                    _state.value = state.value.copy(
                        isLoading = false,
                        error = null,
                        items = result.data?.data ?: emptyList()
                    )
                }
            }
        }.launchIn(screenModelScope)
    }

}