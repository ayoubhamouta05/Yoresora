package com.youppix.ecommercecourse.presentation.home_app.home

import androidx.compose.runtime.Immutable
import com.youppix.ecommercecourse.domain.model.categories.Category
import com.youppix.ecommercecourse.domain.model.items.Item

@Immutable
data class HomeState(
    val isLoading: Boolean = false,
    val searchQuery: String = "",
    val haveNotification: Boolean = false,
    val categories: List<Category> = emptyList(),
    val categorySelected: Int = 1,
    val flashSaleItems: List<Item> = emptyList(),
    val newArrivals: List<Item> = emptyList(),
    val items: List<Item> = emptyList(),
    val getHomeDataError: String? = null,
    val getItemsError: String? = null
)
