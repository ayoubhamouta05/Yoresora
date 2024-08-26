package com.youppix.ecommercecourse.presentation.home_app.cart

import com.youppix.ecommercecourse.domain.model.cart.CartData

data class CartState (
    val userId : Int = 0,
    val isLoading : Boolean = false,
    val cartItems : ArrayList<CartData> = arrayListOf(),
    val subTotal : Float = 0f,
    val deliveryFee : Float = 0f,
    val discount : Float = 0f,
    val totalCost : Float = 0f,
    val promoCode : String = "",
    val promoCodeError : String? = null,
    val getCartError: String? = null ,
    val updateQuantityError : String? = null
)