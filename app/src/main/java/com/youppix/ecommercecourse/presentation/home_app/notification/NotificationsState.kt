package com.youppix.ecommercecourse.presentation.home_app.notification

import com.youppix.ecommercecourse.domain.model.notification.Notification

data class NotificationsState(
    val isLoading: Boolean,
    val error : String? = null ,
    val notifications : List<Notification> = emptyList()
)
