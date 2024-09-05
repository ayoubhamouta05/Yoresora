package com.youppix.ecommercecourse.presentation.starting_app

import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.youppix.ecommercecourse.common.Constant.APP_ENTRY
import com.youppix.ecommercecourse.domain.manager.NetworkConnectivityManager
import com.youppix.ecommercecourse.domain.useCases.appEntry.AppEntryUseCases
import com.youppix.ecommercecourse.domain.useCases.networkConnectivity.NetworkConnectivityManagerUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class StartActivityViewModel @Inject constructor(
    private val appEntryUseCases: AppEntryUseCases,
    private val networkConnectivityManagerUseCase: NetworkConnectivityManagerUseCase
) : ViewModel() {

    private var _appEntry = mutableStateOf("")
    val appEntry: State<String> = _appEntry

    private var _status = mutableStateOf(NetworkConnectivityManager.Status.Unavailable)
    val status: State<NetworkConnectivityManager.Status> = _status

    private var _showNetworkStatus = mutableStateOf(false)
    val showNetworkStatus: State<Boolean> = _showNetworkStatus

    private var firstTime by mutableStateOf(true)


    init {
        handleNetworkConnectivity()
        _appEntry.value = appEntryUseCases.getAppEntryUseCase(APP_ENTRY, _appEntry.value)
    }

    private fun handleNetworkConnectivity() {
        // todo : handle this error here
        networkConnectivityManagerUseCase().onEach {
            _status.value = it
            if (firstTime && status.value == NetworkConnectivityManager.Status.Available) {
                firstTime = false
            } else {
                _showNetworkStatus.value = true
            }
        }.launchIn(viewModelScope)

//        if (status.value == NetworkConnectivityManager.Status.Unavailable) {
//            _showNetworkStatus.value = true
//        }
    }


}