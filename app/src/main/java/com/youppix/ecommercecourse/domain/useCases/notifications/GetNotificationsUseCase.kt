package com.youppix.ecommercecourse.domain.useCases.notifications

import com.youppix.ecommercecourse.domain.repository.notifications.NotificationsRepository

class GetNotificationsUseCase(
    private val notificationsRepository: NotificationsRepository
) {
    suspend operator fun invoke(userId : Int) =
         notificationsRepository.getNotifications(userId = userId)


}