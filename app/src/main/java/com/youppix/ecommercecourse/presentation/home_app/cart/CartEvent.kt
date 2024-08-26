package com.youppix.ecommercecourse.presentation.home_app.cart

sealed class CartEvent {
    data class GetCartItems(val userId: Int) : CartEvent()
    data class OnPromoCodeChange(val promoCode: String) : CartEvent()

    data class UpdateQuantity(
        val userId: Int,
        val itemId: Int,
        val itemSize: Int,
        val itemColor: Int,
        val itemQuantity: Int,
        val index : Int
    ) : CartEvent()
}