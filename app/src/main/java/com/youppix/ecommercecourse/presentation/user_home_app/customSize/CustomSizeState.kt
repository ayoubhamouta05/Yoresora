package com.youppix.ecommercecourse.presentation.user_home_app.customSize

import com.youppix.ecommercecourse.domain.model.details.Size

data class CustomSizeState(
    val isLoading : Boolean = false,
    val size : Size = Size(),
    val initialSize : Size = Size(),
    val errors : CustomSizeErrorsState = CustomSizeErrorsState(),
    val isSuccessful : Boolean = false,
    val showNameErrorDialog : Boolean = false,
    val showErrorDialog : Boolean = false,
    val errorMessage : String ?=null
)
