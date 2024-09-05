package com.youppix.ecommercecourse.presentation.home_app.payment

import com.youppix.ecommercecourse.domain.manager.NetworkConnectivityManager

data class PaymentState(
    val isLoading : Boolean = false,
    val showSnackBar : Boolean = false ,
    val currentUrl : String = "",
    val networkState : NetworkConnectivityManager.Status? = null
)
