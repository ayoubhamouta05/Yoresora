package com.youppix.ecommercecourse.presentation.admin_home_app.details

import androidx.compose.runtime.Immutable
import com.youppix.ecommercecourse.domain.model.details.DetailsData
import com.youppix.ecommercecourse.domain.model.items.Item

@Immutable
data class DetailsState(
    val userId: Int? = null,
    val isLoading: Boolean = false,
    val item: Item = Item(),
    val details: DetailsData = DetailsData(),
    val error: String? = null
)
