package com.youppix.ecommercecourse.presentation.user_home_app.favorites

sealed class FavoriteEvent {

    data class UpdateCategorySelected( val userId: Int , val category:Int ) : FavoriteEvent()
    data object GetAllCategories : FavoriteEvent()


}