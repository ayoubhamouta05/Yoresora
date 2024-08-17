package com.youppix.ecommercecourse.presentation.home_app.customSize

import android.content.Context
import com.youppix.ecommercecourse.domain.model.details.Size

sealed class CustomSizeEvent {

    data class SetInitialSize(val size: Size) : CustomSizeEvent()
    data class UpdateSizeInformation(val size: Size) : CustomSizeEvent()
    data class UpdateErrorsState(val errors: CustomSizeErrorsState) : CustomSizeEvent()
    data class CheckInput(
        val context: Context,
        val name :String,
        val value: String,
        val min: Float,
        val max: Float,
        val changeErrorValue: (String?) -> Unit
    ) : CustomSizeEvent()

    data class UpdateErrorMessage (val error : String?) : CustomSizeEvent()
    data class OnConfirm(val value: CustomSizeState, val context: Context) : CustomSizeEvent()
    data object OnReset : CustomSizeEvent()
    data object HideDialog : CustomSizeEvent()

}