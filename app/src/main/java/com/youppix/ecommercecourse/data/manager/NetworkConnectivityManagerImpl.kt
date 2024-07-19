package com.youppix.ecommercecourse.data.manager

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import com.youppix.ecommercecourse.domain.manager.NetworkConnectivityManager
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch

class NetworkConnectivityManagerImpl(
    private val context: Context
) : NetworkConnectivityManager {

    private val connectivityManager =
        context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

    override fun observe(): Flow<NetworkConnectivityManager.Status> =
        callbackFlow {
            val callback = object : ConnectivityManager.NetworkCallback() {
                override fun onAvailable(network: Network) {
                    super.onAvailable(network)
                    launch { send(NetworkConnectivityManager.Status.Available) }
                }

                override fun onLost(network: Network) {
                    super.onLost(network)
                    launch { send(NetworkConnectivityManager.Status.Lost) }

                }

                override fun onUnavailable() {
                    super.onUnavailable()
                    launch { send(NetworkConnectivityManager.Status.Unavailable) }

                }
            }
            connectivityManager.registerDefaultNetworkCallback(callback)
            awaitClose{
                connectivityManager.unregisterNetworkCallback(callback)
            }
        }.distinctUntilChanged()
}