package com.youppix.ecommercecourse.presentation.home_app.details

import androidx.compose.runtime.Immutable
import com.youppix.ecommercecourse.domain.model.details.DetailsData
import com.youppix.ecommercecourse.domain.model.items.Item

@Immutable
data class DetailsState(
    val isLoading : Boolean = false,
    val item : Item = Item(),
    val details : DetailsData = DetailsData(),
    val selectedSize : Int = 0,
    val selectedColors : Int = 0,
    val error : String ? = null
)
