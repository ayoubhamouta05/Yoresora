package com.youppix.ecommercecourse.presentation.home_app.details

import androidx.compose.runtime.Immutable
import com.youppix.ecommercecourse.domain.model.details.DetailsData
import com.youppix.ecommercecourse.domain.model.items.Item

@Immutable
data class DetailsState(
    val userId: Int? = null,
    val isLoading: Boolean = false,
    val item: Item = Item(),
    val details: DetailsData = DetailsData(),
    val selectedSize: Int = 0,
    val selectedColor: Int = 0,
    val sizeAlreadyExistDialog: Boolean = false,
    val goToCustomSizeScreen : Boolean =false,
    val checkSizeLoading : Boolean= false,
    val addToCartState : Boolean = true,
    val error: String? = null
)
