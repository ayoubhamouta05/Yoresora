package com.youppix.ecommercecourse.presentation.home_app.checkout

import androidx.compose.runtime.Immutable
import com.youppix.ecommercecourse.domain.model.address.Address

@Immutable
data class CheckoutState(
    val isLoading : Boolean = false,
    val customerId : String? = null,
    val error : String? = null,
    val checkoutUrl : String?=null,
    val address : Address? = null,
    val subTotal : Float = 0f,
    val totalPrice : Float = 0f ,
    val homeDeliveryFee : Float = 0f ,
    val pickupDeliveryFee : Float = 0f,
    val isHomeDelivery : Boolean = true
)
