package com.youppix.ecommercecourse.presentation.home_app.checkout

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import com.youppix.ecommercecourse.common.Resource
import com.youppix.ecommercecourse.domain.model.cart.CartData
import com.youppix.ecommercecourse.domain.model.items.Item
import com.youppix.ecommercecourse.domain.useCases.checkout.CheckoutUseCases
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

class CheckoutViewModel @Inject constructor(
    private val checkoutUseCases: CheckoutUseCases,
) : ScreenModel {

    private var _state = mutableStateOf(CheckoutState(true))
    val state: State<CheckoutState> = _state


    fun onEvent(event: CheckoutEvent) {
        when (event) {
            is CheckoutEvent.UpdateDeliveryMethod -> {
                _state.value = state.value.copy(
                    isHomeDelivery = event.value,
                    totalPrice = (if (event.value)
                        state.value.homeDeliveryFee
                    else state.value.pickupDeliveryFee) + state.value.subTotal
                )
            }

            is CheckoutEvent.GetAddress -> {
                screenModelScope.launch {
                    getAddress(event.userId)
                }
            }

            is CheckoutEvent.SetSubTotal -> {
                _state.value = state.value.copy(
                    subTotal = event.value
                )
            }

            is CheckoutEvent.CreateCheckoutUrl -> {
                screenModelScope.launch {
                    createCheckoutUrl(
                        local = event.local ,
                        description = event.description ,
                        amount = event.amount ,
                        customerId = event.customerId ,
                        userId = event.userId,
                        carts = event.carts
                    )
                }
            }

            is CheckoutEvent.UpdateCustomerId -> {
                _state.value = state.value.copy(
                    customerId = event.customerId
                )
            }

            CheckoutEvent.ResetCheckoutUrl -> {
                _state.value = state.value.copy(
                    checkoutUrl = null
                )
            }
        }
    }

    private suspend fun getAddress(userId: Int) {
        checkoutUseCases.getAddress(userId).onEach { result ->
            when (result) {
                is Resource.Loading -> {
                    _state.value = state.value.copy(
                        isLoading = true
                    )
                }

                is Resource.Error -> {
                    _state.value = state.value.copy(
                        isLoading = false
                    )
                }

                is Resource.Successful -> {
                    _state.value = state.value.copy(
                        isLoading = false,
                        error = null,
                        address = result.data?.data,
                        homeDeliveryFee = result.data?.deliveryHomePrice ?: 0f,
                        pickupDeliveryFee = result.data?.deliveryPickupPointPrice ?: 0f,
                        totalPrice = (if (state.value.isHomeDelivery)
                            result.data?.deliveryHomePrice ?: 0f
                        else result.data?.deliveryPickupPointPrice ?: 0f
                                ) + state.value.subTotal
                    )
                }
            }

        }.launchIn(screenModelScope)
    }


    private suspend fun createCheckoutUrl(
        local: String,
        description: String,
        amount: Float,
        customerId: String,
        userId: Int,
        carts : List<CartData>
    ) {
        checkoutUseCases.createCheckoutUrl(
            local = local,
            description = description,
            amount = amount,
            customerId = customerId ,
            userId = userId,
            carts = carts
        ).onEach {result ->
            when (result ){
                is Resource.Loading -> {
                    _state.value = state.value.copy(
                        isLoading = true
                    )

                }
                is Resource.Error ->{
                    _state.value = state.value.copy(
                        isLoading = false,
                        error = result.data?.message ?: result.message ?: "An unexpected error occurred",
                        checkoutUrl = null
                    )
                }
                is Resource.Successful -> {
                    _state.value = state.value.copy(
                        isLoading = false ,
                        error = null ,
                        checkoutUrl = result.data?.data
                    )
                }
            }
        }.launchIn(screenModelScope)
    }

}