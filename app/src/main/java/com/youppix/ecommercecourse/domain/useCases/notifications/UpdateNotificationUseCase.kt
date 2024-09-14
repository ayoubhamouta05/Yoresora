package com.youppix.ecommercecourse.domain.useCases.notifications

import com.youppix.ecommercecourse.domain.repository.notifications.NotificationsRepository

class UpdateNotificationUseCase (
    private val notificationsRepository: NotificationsRepository
) {
    suspend operator fun invoke(userId : Int) =
        notificationsRepository.updateNotification(userId = userId)


}