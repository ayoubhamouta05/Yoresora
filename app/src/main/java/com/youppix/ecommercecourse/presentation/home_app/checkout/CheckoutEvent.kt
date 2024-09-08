package com.youppix.ecommercecourse.presentation.home_app.checkout

import com.youppix.ecommercecourse.domain.model.cart.CartData
import com.youppix.ecommercecourse.domain.model.items.Item

sealed class CheckoutEvent {

    data class UpdateDeliveryMethod(val value: Boolean) : CheckoutEvent()

    data class GetAddress(val userId: Int) : CheckoutEvent()
    data object ResetCheckoutUrl : CheckoutEvent()
    data class SetSubTotal(val value: Float) : CheckoutEvent()
    data class UpdateCustomerId(val customerId: String) : CheckoutEvent()
    data class CreateCheckoutUrl(
        val local: String,
        val description: String,
        val amount: Float,
        val customerId: String,
        val userId: Int,
        val carts : List<CartData>
    ) : CheckoutEvent()
}