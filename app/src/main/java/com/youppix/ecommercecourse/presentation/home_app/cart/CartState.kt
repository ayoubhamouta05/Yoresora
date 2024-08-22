package com.youppix.ecommercecourse.presentation.home_app.cart

import com.youppix.ecommercecourse.domain.model.CartItemData

data class CartState (
    val isLoading : Boolean = false ,
    val cartItems : List<CartItemData> = emptyList() ,
    val subTotal : Float = 0f ,
    val deliveryFee : Float = 0f ,
    val discount : Float = 0f ,
    val totalCost : Float = 0f ,
    val promoCode : String = "" ,
    val promoCodeError : String? = null,
)