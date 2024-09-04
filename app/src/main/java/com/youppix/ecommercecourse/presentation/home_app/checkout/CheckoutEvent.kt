package com.youppix.ecommercecourse.presentation.home_app.checkout

sealed class CheckoutEvent {

    data class UpdateDeliveryMethod (val value : Boolean) : CheckoutEvent()

    data class GetAddress (val userId : Int) : CheckoutEvent()

    data class SetSubTotal (val value : Float) : CheckoutEvent()
}