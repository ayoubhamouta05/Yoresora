package com.youppix.ecommercecourse.domain.model.details

import androidx.compose.runtime.Immutable
import com.youppix.ecommercecourse.domain.model.items.ColorData
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Immutable
@Serializable
data class DetailsData(
    val categories_name: String = "",
    val categories_name_ar: String = "",
    val colors : List<ColorData> = emptyList(),
    val images: List<String> = emptyList(),
    val is_favorite: Boolean = false,
    val is_active : Boolean = true ,
    val sizes: List<SizeData> = emptyList(),
    val initialData: ArrayList<InitialColorAndSizeData>? = null,
)

@Immutable
@Serializable
data class InitialColorAndSizeData(
    @SerialName("item_size")
    val itemSize: Int,
    @SerialName("item_color")
    val itemColor: Int,
)