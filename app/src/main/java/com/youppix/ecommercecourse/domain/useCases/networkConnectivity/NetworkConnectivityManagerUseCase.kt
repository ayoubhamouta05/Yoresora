package com.youppix.ecommercecourse.domain.useCases.networkConnectivity

import com.youppix.ecommercecourse.domain.manager.NetworkConnectivityManager
import kotlinx.coroutines.flow.Flow

class NetworkConnectivityManagerUseCase(
    private val networkConnectivityManager: NetworkConnectivityManager
) {

    operator fun invoke() : Flow<NetworkConnectivityManager.Status>{
        return networkConnectivityManager.observe()
    }

}