package com.youppix.ecommercecourse.presentation.home_app.cart

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import com.youppix.ecommercecourse.common.Resource
import com.youppix.ecommercecourse.domain.useCases.cart.CartUseCases
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

class CartViewModel @Inject constructor(
    private val cartUseCases: CartUseCases,
) : ScreenModel {

    private var _state = mutableStateOf(CartState(isLoading = true))
    val state: State<CartState> = _state


    fun onEvent(event: CartEvent) {
        when (event) {

            is CartEvent.GetCartItems -> {
                screenModelScope.launch {
                    getCartItems(event.userId)
                }
            }

            is CartEvent.OnPromoCodeChange -> {
                _state.value = state.value.copy(
                    promoCode = event.promoCode
                )
            }

            is CartEvent.UpdateQuantity -> {
                screenModelScope.launch {
                    updateQuantity(
                        userId = event.userId,
                        itemId = event.itemId,
                        itemSize = event.itemSize,
                        itemColor = event.itemColor,
                        itemQuantity = event.itemQuantity,
                        index = event.index
                    )
                }
            }
        }
    }


    private suspend fun getCartItems(userId: Int) {
        cartUseCases.getCartItems(userId).onEach { result ->
            when (result) {
                is Resource.Loading -> {
                    _state.value = state.value.copy(
                        isLoading = true
                    )
                }

                is Resource.Error -> {
                    _state.value = state.value.copy(
                        isLoading = false, getCartError = result.message
                    )
                }

                is Resource.Successful -> {
                    _state.value = state.value.copy(
                        isLoading = false,
                        cartItems = result.data?.data ?: emptyList(),
                        subTotal = result.data?.subTotal ?: state.value.subTotal,
                        deliveryFee = result.data?.deliveryFee ?: state.value.deliveryFee,
                        discount = result.data?.discount ?: 0f ,
                        totalCost = result.data?.totalCost ?: state.value.totalCost
                    )
                }
            }
        }.launchIn(screenModelScope)
    }

    fun setUserId(userId: Int) {
        _state.value = state.value.copy(
            userId = userId
        )
    }

    private suspend fun updateQuantity(
        userId: Int,
        itemId: Int,
        itemSize: Int,
        itemColor: Int,
        itemQuantity: Int,
        index: Int,
    ) {

        updateValues(index = index , itemQuantity = itemQuantity)

        cartUseCases.updateQuantity(
            userId, itemId, itemSize, itemColor, itemQuantity
        ).onEach { result ->
            when (result) {
                is Resource.Loading -> {
                }

                is Resource.Error -> {
                    _state.value = state.value.copy(
                        isLoading = false,
                        updateQuantityError = result.data?.message ?: result.message
                        ?: "Unknown Error Occurred",

                    )
                }

                is Resource.Successful -> {
                    _state.value = state.value.copy(
                        isLoading = false,
                    )
                }
            }
        }.launchIn(screenModelScope)
    }

    private fun updateValues(index : Int , itemQuantity: Int) {
        val initialQuantity = state.value.cartItems[index].item_quantity
        val initialItems = state.value.cartItems

        _state.value = state.value.copy(cartItems = state.value.cartItems.mapIndexed { i, value ->
            if (index == i) state.value.cartItems[i].copy(item_quantity = itemQuantity) else value
        })

        if (itemQuantity <= 0) {
            _state.value =
                state.value.copy(cartItems = state.value.cartItems.filterIndexed { i, _ -> i != index } ,
                    subTotal = state.value.subTotal - state.value.cartItems[index].items_price)
        } else {
            _state.value = state.value.copy(
                subTotal = state.value.subTotal -
                        (initialItems[index].items_price * initialQuantity) +
                        (state.value.cartItems[index].items_price * itemQuantity)
            )
        }

        _state.value = state.value.copy(
            totalCost = state.value.subTotal +
                    state.value.deliveryFee -
                    state.value.discount
        )
    }


}