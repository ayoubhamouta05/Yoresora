package com.youppix.ecommercecourse.presentation.user_home_app.cart

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import com.youppix.ecommercecourse.common.Resource
import com.youppix.ecommercecourse.domain.model.cart.CartData
import com.youppix.ecommercecourse.domain.model.cart.PromoCodeData
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

            is CartEvent.OnCheckCodePromo -> {
                screenModelScope.launch {
                    checkCodePromo(event.promoCode)
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

                    if (state.value.promoCodeList.isNotEmpty()) {

                        for (item in state.value.promoCodeList) {
                            calculateDiscountAfterPromoCodeEntered(
                                item.percent ,
                                item.itemId,
                                item.itemId,
                                item.itemQuantity,
                                item.itemPrice
                            )
                        }
                    } else {
                        _state.value = state.value.copy(
                            isLoading = false,
                            cartItems = result.data?.data ?: emptyList(),
                            subTotal = result.data?.subTotal ?: state.value.subTotal,
                            discount = result.data?.discount ?: 0f,
                            totalCost = result.data?.totalCost ?: state.value.totalCost
                        )
                    }
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

        updateValues(index = index, itemQuantity = itemQuantity)

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

    private suspend fun checkCodePromo(codePromo: String) {
        Log.d("CartViewModel", "before" + _state.value.promoCodeList.size.toString())
        cartUseCases.checkCodePromo(codePromo).onEach { result ->
            when (result) {
                is Resource.Loading -> {
                    _state.value = state.value.copy(
                        isLoading = true
                    )
                }

                is Resource.Error -> {
                    _state.value = state.value.copy(
                        isLoading = false,
                        promoCodeError = result.data?.message ?: result.message
                        ?: "Unknown Error Occurred",
                    )
                }

                is Resource.Successful -> {
                    _state.value = state.value.copy(
                        isLoading = false,
                        promoCodeError = null
                    )

                    if (result.data?.item_id != null) {
                        _state.value.promoCodeList.add(
                            PromoCodeData(
                                promoCode = codePromo,
                                itemId = result.data.item_id,
                                percent = result.data.promo_percent,
                                itemPrice = result.data.item_price ,
                                itemQuantity = result.data.item_quantity
                            )
                        )
                    }


//                    for (item in state.value.cartItems) {
//                        calculateDiscountAfterPromoCodeEntered(
//                            result.data?.promo_percent ?: 0,
//                            result.data?.item_id ?: -1,
//                            item.item_id,
//                            item.item_quantity,
//                            item.items_price
//                        )
//                    }

                    Log.d("CartViewModel", "after " + _state.value.promoCodeList.size.toString())


                }
            }
        }.launchIn(screenModelScope)

    }

    private fun updateValues(index: Int, itemQuantity: Int) {
        val initialQuantity = state.value.cartItems[index].item_quantity
        val initialItems = state.value.cartItems

        _state.value = state.value.copy(cartItems = state.value.cartItems.mapIndexed { i, value ->
            if (index == i) state.value.cartItems[i].copy(item_quantity = itemQuantity) else value
        })

        if (itemQuantity <= 0) {
            _state.value =
                state.value.copy(
                    cartItems = state.value.cartItems.filterIndexed { i, _ -> i != index },
                    subTotal = calculateSubTotal(
                        itemQuantity,
                        index,
                        initialItems,
                        initialQuantity
                    ),
                    discount = calculateDiscount(itemQuantity, index, initialItems, initialQuantity)
                )
        } else {
            _state.value = state.value.copy(
                subTotal = calculateSubTotal(itemQuantity, index, initialItems, initialQuantity),
                discount = calculateDiscount(itemQuantity, index, initialItems, initialQuantity)
            )
        }

        if (state.value.promoCode.isNotEmpty()) {
            onEvent(CartEvent.OnCheckCodePromo(state.value.promoCode))
        } else {
            _state.value = state.value.copy(
                totalCost = calculateTotalCost()
            )
        }


    }

    private fun calculateDiscount(
        itemQuantity: Int,
        index: Int,
        initialItems: List<CartData>,
        initialQuantity: Int,
    ): Float {
        return if (itemQuantity <= 0) {
            state.value.discount - ((state.value.cartItems[index].items_price * state.value.cartItems[index].items_discount) / 100)
        } else {
            state.value.discount -
                    (((initialItems[index].items_price * initialItems[index].items_discount) / 100) * initialQuantity) +
                    (((state.value.cartItems[index].items_price * state.value.cartItems[index].items_discount) / 100) * itemQuantity)

        }
    }

    private fun calculateTotalCost(): Float {
        return state.value.subTotal -
                state.value.discount
    }

    private fun calculateSubTotal(
        itemQuantity: Int,
        index: Int,
        initialItems: List<CartData>,
        initialQuantity: Int,
    ): Float {
        return if (itemQuantity <= 0) {
            state.value.subTotal - state.value.cartItems[index].items_price
        } else {
            state.value.subTotal -
                    (initialItems[index].items_price * initialQuantity) +
                    (state.value.cartItems[index].items_price * itemQuantity)
        }
    }

    private fun calculateDiscountAfterPromoCodeEntered(
        promoPercentage: Int,
        promoItemId: Int,
        itemId: Int,
        itemQuantity: Int,
        itemPrice: Int,
    ): Int {
        if (itemId == promoItemId) {
            val discount = (itemPrice * promoPercentage / 100) * itemQuantity.toFloat()
            _state.value = state.value.copy(
                discount = discount + state.value.discount,
                totalCost = calculateTotalCost() - discount
            )

        }
        return 0

//        return if (itemId == promoItemId)
//                (itemPrice * promoPercentage / 100) * itemQuantity
//        else
//            0
    }


}