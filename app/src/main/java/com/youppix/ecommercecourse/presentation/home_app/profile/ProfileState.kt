package com.youppix.ecommercecourse.presentation.home_app.profile

import com.youppix.ecommercecourse.domain.model.user.User

data class ProfileState(
    val user: User = User(),
    val isLoading : Boolean = false ,
    val showDialog : Boolean = false ,
    val isNotificationEnable : Boolean = false ,
)
