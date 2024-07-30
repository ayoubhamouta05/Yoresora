package com.youppix.ecommercecourse.domain.model.categories

import kotlinx.serialization.Serializable

@Serializable
data class CategoryData(
    val categories_datetime: String,
    val categories_id: Int,
    val categories_image: String,
    val categories_name: String,
    val categories_name_ar: String
)



