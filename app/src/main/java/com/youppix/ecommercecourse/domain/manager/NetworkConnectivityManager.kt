package com.youppix.ecommercecourse.domain.manager

import kotlinx.coroutines.flow.Flow

interface NetworkConnectivityManager {
    fun observe(): Flow<Status>

    enum class Status {
        Available, Unavailable, Lost
    }
}