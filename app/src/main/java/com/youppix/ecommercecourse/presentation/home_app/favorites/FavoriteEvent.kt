package com.youppix.ecommercecourse.presentation.home_app.favorites

sealed class FavoriteEvent {

    data class UpdateCategorySelected( val userId: Int , val category:Int ) : FavoriteEvent()
    data class GetAllFavorites(val userId: Int, val categoryId: Int) : FavoriteEvent()
    data object GetAllCategories : FavoriteEvent()


}