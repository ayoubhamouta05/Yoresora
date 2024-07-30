package com.youppix.ecommercecourse.data.remote.home.dto

import com.youppix.ecommercecourse.domain.model.items.ItemData
import kotlinx.serialization.Serializable

@Serializable
data class ItemsResponse(
    val data: List<ItemData>?,
    val message: String,
    val status: String
)