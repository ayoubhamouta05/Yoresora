package com.youppix.ecommercecourse.data.remote.home.dto

import com.youppix.ecommercecourse.domain.model.categories.CategoryData
import com.youppix.ecommercecourse.domain.model.items.ItemData
import kotlinx.serialization.Serializable

@Serializable
data class HomeResponse(
    val categories: List<CategoryData>? = emptyList(),
    val items: List<ItemData>? = emptyList(),
    val status: String
)
