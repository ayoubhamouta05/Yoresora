package com.youppix.ecommercecourse.domain.model.categories

import androidx.compose.runtime.Immutable

@Immutable
data class Category(
    val id : Int ,
    val name : String,
    val nameAr : String,
    val image : String
)

fun CategoryData.toCategory() : Category{
    return Category(
        id= categories_id,
        name = categories_name,
        nameAr = categories_name_ar,
        image = categories_image
    )
}

fun List<CategoryData>.toCategories() : List<Category>{
    return this.map { it.toCategory() }
}