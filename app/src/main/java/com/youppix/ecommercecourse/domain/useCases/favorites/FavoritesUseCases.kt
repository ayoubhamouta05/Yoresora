package com.youppix.ecommercecourse.domain.useCases.favorites


data class FavoritesUseCases(
    val getAllFavorites: GetAllFavoritesUseCase,
    val getAllCategories: GetAllCategoriesUseCase,
    val addOrDeleteFavorite: AddOrDeleteFavoriteUseCase,
)