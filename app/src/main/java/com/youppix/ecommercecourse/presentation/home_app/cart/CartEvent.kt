package com.youppix.ecommercecourse.presentation.home_app.cart

sealed class CartEvent {
    data class OnPromoCodeChange (val promoCode : String ) : CartEvent()
}