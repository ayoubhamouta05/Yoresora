package com.youppix.ecommercecourse.presentation.user_home_app.payment

import com.youppix.ecommercecourse.domain.manager.NetworkConnectivityManager

data class PaymentState(
    val isLoading : Boolean = false,
    val showSnackBar : Boolean = false ,
    val networkState : NetworkConnectivityManager.Status? = null
)
