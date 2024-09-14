package com.youppix.ecommercecourse.data.repository.notifications

import com.youppix.ecommercecourse.common.Resource
import com.youppix.ecommercecourse.data.remote.home.HomeService
import com.youppix.ecommercecourse.data.remote.home.dto.NotificationsResponse
import com.youppix.ecommercecourse.domain.repository.notifications.NotificationsRepository
import kotlinx.coroutines.flow.Flow

class NotificationsRepositoryImpl(private val service: HomeService) : NotificationsRepository {
    override suspend fun getNotifications(userId: Int): Flow<Resource<NotificationsResponse>> {
        return service.getNotifications(userId = userId)
    }

    override suspend fun updateNotification(userId: Int){
        service.updateNotifications(userId = userId)
    }
}