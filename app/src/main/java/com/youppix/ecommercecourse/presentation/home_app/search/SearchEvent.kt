package com.youppix.ecommercecourse.presentation.home_app.search

import androidx.compose.runtime.Immutable
import com.youppix.ecommercecourse.domain.model.items.FilteringItems

@Immutable
sealed class SearchEvent {
    @Immutable
    data class UpdateSearchQuery(val value: String) : SearchEvent()

    @Immutable
    data class UpdateCategorySelected(val id: Int) : SearchEvent()

    @Immutable
    data class UpdateFilteringItems(
        val filteringItems: FilteringItems,
        val sendRequest: Boolean = true
    ) : SearchEvent()

    @Immutable
    data object GetAllCategories : SearchEvent()

    @Immutable
    data class GetItemsByFiltering(val filteringItems: FilteringItems) : SearchEvent()
}