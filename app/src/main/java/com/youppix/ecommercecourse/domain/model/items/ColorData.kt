package com.youppix.ecommercecourse.domain.model.items

import kotlinx.serialization.Serializable

@Serializable
data class ColorData(
    val color_name_ar: String,
    val colors_hex: String,
    val colors_id: Int,
    val colors_name: String
)