package com.youppix.ecommercecourse.presentation.home_app.home

import androidx.compose.runtime.Immutable


@Immutable
sealed class HomeEvent {

    @Immutable
    data class UpdateCategorySelected(val id: Int) : HomeEvent()

    @Immutable
    data object GetHomeData : HomeEvent()


    @Immutable
    data class GetItemsByCategory(val category: Int) : HomeEvent()

}