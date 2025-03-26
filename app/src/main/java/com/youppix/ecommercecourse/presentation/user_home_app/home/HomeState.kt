package com.youppix.ecommercecourse.presentation.user_home_app.home

import androidx.compose.runtime.Immutable
import com.youppix.ecommercecourse.domain.model.categories.Category
import com.youppix.ecommercecourse.domain.model.items.Item

@Immutable
data class HomeState(
    val isHomeLoading: Boolean = false,
    val isItemsCategoriesLoading: Boolean = false,
    val haveNotification: Boolean = false,
    val categories: List<Category> = emptyList(),
    val userId : Int = 0 ,
    val categorySelected: Int = 0,
    val flashSaleItems: List<Item> = emptyList(),
    val newArrivals: List<Item> = emptyList(),
    val items: List<Item> = emptyList(),
    val getHomeDataError: String? = null,
    val getItemsError: String? = null
)
