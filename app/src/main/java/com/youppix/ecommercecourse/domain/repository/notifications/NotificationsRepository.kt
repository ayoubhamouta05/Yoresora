package com.youppix.ecommercecourse.domain.repository.notifications

import com.youppix.ecommercecourse.common.Resource
import com.youppix.ecommercecourse.data.remote.home.dto.NotificationsResponse
import kotlinx.coroutines.flow.Flow

interface NotificationsRepository  {

    suspend fun getNotifications(userId : Int) : Flow<Resource<NotificationsResponse>>
    suspend fun updateNotification(userId : Int)

}