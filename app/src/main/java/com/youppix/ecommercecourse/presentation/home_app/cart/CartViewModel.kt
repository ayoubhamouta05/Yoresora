package com.youppix.ecommercecourse.presentation.home_app.cart

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import com.youppix.ecommercecourse.common.Resource
import com.youppix.ecommercecourse.data.remote.auth.dto.AuthResponse
import com.youppix.ecommercecourse.domain.useCases.cart.CartUseCases
import kotlinx.coroutines.flow.Flow
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
                        itemQuantity = event.itemQuantity ,
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
                        isLoading = false,
                        getCartError = result.message
                    )
                }

                is Resource.Successful -> {
                    _state.value = state.value.copy(
                        isLoading = false,
                        cartItems = result.data?.data ?: arrayListOf()
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
        index : Int
    ) {
        cartUseCases.updateQuantity(
            userId, itemId, itemSize, itemColor, itemQuantity
        ).onEach { result ->
            when (result) {
                is Resource.Loading -> {
                    _state.value = state.value.copy(
                        isLoading = true
                    )
                }

                is Resource.Error -> {
                    _state.value = state.value.copy(
                        isLoading = false,
                        updateQuantityError = result.data?.message ?: result.message
                        ?: "Unknown Error Occurred"
                    )

                }

                is Resource.Successful -> {
                    _state.value = state.value.copy(
                        isLoading = false
                    )
                    state.value.cartItems[index].item_quantity = itemQuantity
                    if (itemQuantity <=0){
                        state.value.cartItems.removeAt(index)
                    }
                }
            }
        }.launchIn(screenModelScope)
    }


}