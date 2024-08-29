package com.youppix.ecommercecourse.presentation.home_app.checkout

import androidx.compose.runtime.Immutable
import com.youppix.ecommercecourse.domain.model.address.Address

@Immutable
data class CheckoutState(
    val isLoading : Boolean = false,
    val error : String? = null,
    val address : Address? = null,
    val isHomeDelivery : Boolean = true
)
