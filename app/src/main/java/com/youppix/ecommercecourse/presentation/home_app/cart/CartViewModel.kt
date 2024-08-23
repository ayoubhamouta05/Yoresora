package com.youppix.ecommercecourse.presentation.home_app.cart

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import com.youppix.ecommercecourse.common.Resource
import com.youppix.ecommercecourse.domain.model.cart.CartItemData
import com.youppix.ecommercecourse.domain.model.items.ColorData
import com.youppix.ecommercecourse.domain.model.items.Item
import com.youppix.ecommercecourse.domain.useCases.cart.CartUseCases
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

class CartViewModel @Inject constructor(
    private val cartUseCases: CartUseCases,
) : ScreenModel {

    private var _state = mutableStateOf(CartState())
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
        }
    }


    private suspend fun getCartItems(userId: Int) {
        cartUseCases.getCarts(userId).onEach { result ->
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

//    private suspend fun addOrDeleteCartItem(
//        itemId: Int,
//        userId: Int,
//        itemSize: Int,
//        itemColor: Int,
//    ) {
//        cartUseCases.addOrDeleteCart(itemId, userId, itemSize, itemColor).onEach {result->
//            when(result){
//                is Resource.Loading -> {
//                }
//
//                is Resource.Error -> {
//                    _state.value = state.value.copy(
//                        isLoading = false,
//                    )
//                }
//                is Resource.Successful -> {
//                    _state.value = state.value.copy(
//                        isLoading = false,
//                    )
//                }
//            }
//        }.launchIn(screenModelScope)
//    }


}