package com.youppix.ecommercecourse.presentation.home_app.home


sealed class HomeEvent {

    data class UpdateCategorySelected(val id: Int) : HomeEvent()

    data class GetHomeData(val userId: Int) : HomeEvent()

    data class GetItemsByCategory(val category: Int) : HomeEvent()

    data class SetUserId(val userId: Int) : HomeEvent()

}