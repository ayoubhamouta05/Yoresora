package com.youppix.ecommercecourse.presentation.home_app.checkout

import com.youppix.ecommercecourse.domain.model.checkout.Address

data class CheckoutState(
    val isLoading : Boolean = false ,
    val error : String? = null ,
    val shippingAddress : Address? = null ,
    val isHomeDelivery : Boolean = true
)
