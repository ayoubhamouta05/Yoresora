package com.youppix.ecommercecourse.presentation.home_app.payment

import com.youppix.ecommercecourse.domain.manager.NetworkConnectivityManager

sealed class PaymentEvent {

    data class ToggleLoading (val value : Boolean) : PaymentEvent()
    data class ToggleShowSnackBar (val value : Boolean) : PaymentEvent()
    data class ToggleNetworkState (val value : NetworkConnectivityManager.Status?) : PaymentEvent()
    data object HandleNetworkState : PaymentEvent()

}