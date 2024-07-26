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


fun CategoryData.toCategory() : Category{
    return Category(
        name = categories_name,
        nameAr = categories_name_ar,
        image = categories_image
    )
}

fun List<CategoryData>.toCategories() : List<Category>{
    return this.map { it.toCategory() }
}
