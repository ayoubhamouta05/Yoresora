package com.youppix.ecommercecourse.presentation.home_app.orders

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import com.youppix.ecommercecourse.common.Resource
import com.youppix.ecommercecourse.domain.useCases.orders.OrdersUseCases
import io.ktor.util.toLowerCasePreservingASCIIRules
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

class OrdersViewModel @Inject constructor(
    private val ordersUseCases: OrdersUseCases,
) : ScreenModel {

    private var _state = mutableStateOf(OrdersState(isLoading = true))
    val state: State<OrdersState> = _state

    fun onEvent(event: OrdersEvent) {
        when (event) {
            is OrdersEvent.UpdateOrderType -> {
                val orderStatus = when (event.type) {
                    OrdersType.PAID ->OrdersType.PAID.name
                    OrdersType.COMPLETED ->OrdersType.COMPLETED.name
                    else -> {
                        OrdersType.UNFINISHED.name
                    }
                }


                _state.value = state.value.copy(
                    ordersType = event.type,
                    ordersList = state.value.allOrders.filter {
                        when (orderStatus){
                            OrdersType.UNFINISHED.name -> it.ordersStatus !in listOf(OrdersType.PAID.name.lowercase(),
                                OrdersType.PAID.name.lowercase()
                            )
                            else -> it.ordersStatus == orderStatus.lowercase()
                        }
                    }
                )



            }

            is OrdersEvent.GetAllOrders -> {
                screenModelScope.launch {
                    getAllOrders(event.userId)
                }
            }
        }
    }

    private suspend fun getAllOrders(userId: Int) {
        ordersUseCases.getAllOrders(userId).onEach { result ->
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
                    val orderStatus = when (state.value.ordersType) {
                        OrdersType.PAID ->OrdersType.PAID.name
                        OrdersType.COMPLETED ->OrdersType.COMPLETED.name
                        else -> {
                            OrdersType.UNFINISHED.name
                        }
                    }
                    _state.value = state.value.copy(
                        isLoading = false,
                        error = null,
                        allOrders = result.data?.data ?: emptyList(),
                        ordersList = result.data?.data?.filter {
                            when (orderStatus){
                                OrdersType.UNFINISHED.name -> it.ordersStatus !in listOf(OrdersType.PAID.name.lowercase(),
                                    OrdersType.PAID.name.lowercase()
                                )
                                else -> it.ordersStatus == orderStatus.lowercase()
                            }
                        }
                            ?: emptyList()
                    )
                }
            }
        }.launchIn(screenModelScope)
    }

}