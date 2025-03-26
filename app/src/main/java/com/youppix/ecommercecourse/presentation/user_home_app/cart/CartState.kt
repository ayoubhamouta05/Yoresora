package com.youppix.ecommercecourse.presentation.user_home_app.cart

import com.youppix.ecommercecourse.domain.model.cart.CartData
import com.youppix.ecommercecourse.domain.model.cart.PromoCodeData

data class CartState (
    val userId : Int = 0,
    val isLoading : Boolean = false,
    val cartItems : List<CartData> = emptyList(),
    val subTotal : Float = 0f,
    val deliveryFee : Float = 0f,
    val discount : Float = 0f,
    val promoPercent: Float = 0f,
    val totalCost : Float = 0f,
    val promoCode : String = "",
    val promoCodeList : ArrayList<PromoCodeData> = arrayListOf(),
    val promoCodeError : String? = "",
    val getCartError: String? = null,
    val updateQuantityError : String? = null
)