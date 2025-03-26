package com.youppix.ecommercecourse.presentation.admin_home_app.users

import com.youppix.ecommercecourse.domain.model.user.User

data class UserState(
    val isLoading : Boolean = false,
    val users : List<User> = emptyList()

)
