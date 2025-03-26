package com.youppix.ecommercecourse.presentation.user_home_app.search

import androidx.compose.runtime.Immutable
import com.youppix.ecommercecourse.domain.model.categories.Category
import com.youppix.ecommercecourse.domain.model.items.ColorData
import com.youppix.ecommercecourse.domain.model.items.FilteringItems
import com.youppix.ecommercecourse.domain.model.items.Item

@Immutable
data class SearchState(
    val categoriesLoading: Boolean = false,
    val itemsLoading: Boolean = false,
    val getCategoriesError: String? = null,
    val getItemsError: String? = null,
    val items: List<Item> = emptyList(),
    val categories: List<Category> = emptyList(),
    val filteringItems: FilteringItems = FilteringItems() ,
    val allColors : List<ColorData> = emptyList()
)
