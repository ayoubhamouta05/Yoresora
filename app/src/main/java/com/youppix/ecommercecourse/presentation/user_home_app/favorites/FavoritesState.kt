package com.youppix.ecommercecourse.presentation.user_home_app.favorites

import androidx.compose.runtime.Immutable
import com.youppix.ecommercecourse.domain.model.categories.Category
import com.youppix.ecommercecourse.domain.model.items.Item

@Immutable
data class FavoritesState(
    val categoriesLoading : Boolean = false,
    val itemsLoading : Boolean = false,
    val categories : List<Category> = emptyList(),
    val items : List<Item> = emptyList(),
    val categorySelected : Int = 0,
    val userId : Int? = null,
    val itemsError : String ? = null,
    val categoriesError : String ? = null

)
