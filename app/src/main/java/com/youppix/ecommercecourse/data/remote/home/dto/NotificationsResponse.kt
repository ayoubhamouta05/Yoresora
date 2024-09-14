package com.youppix.ecommercecourse.data.remote.home.dto

import com.youppix.ecommercecourse.domain.model.notification.NotificationsData
import kotlinx.serialization.Serializable

@Serializable
data class NotificationsResponse(
    val data: List<NotificationsData>?= null,
    val message: String,
    val status: String
)