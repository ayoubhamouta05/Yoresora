package com.youppix.ecommercecourse.data.remote.home.dto

import com.youppix.ecommercecourse.domain.model.items.ColorData
import kotlinx.serialization.Serializable

@Serializable
data class ColorResponse(
    val data: List<ColorData>,
    val message: String,
    val status: String
)