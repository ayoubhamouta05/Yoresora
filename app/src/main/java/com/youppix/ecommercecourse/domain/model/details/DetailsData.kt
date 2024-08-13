package com.youppix.ecommercecourse.domain.model.details

import androidx.compose.runtime.Immutable
import kotlinx.serialization.Serializable

@Immutable
@Serializable
data class DetailsData(
    val categories_name: String = "",
    val categories_name_ar: String = "",
    val colors_name_ar: List<String> = emptyList(),
    val colors_name: List<String> = emptyList(),
    val colors_hex : List<String> = emptyList(),
    val colors_id : List<Int> = emptyList(),
    val images : ArrayList<String> = arrayListOf(),
    val is_favorite: Boolean = false,
    val sizes : List<SizeData> = emptyList(),
)