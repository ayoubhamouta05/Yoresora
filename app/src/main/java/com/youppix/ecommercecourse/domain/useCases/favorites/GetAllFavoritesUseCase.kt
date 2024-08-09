package com.youppix.ecommercecourse.domain.useCases.favorites

import com.youppix.ecommercecourse.domain.repository.favorites.FavoritesRepository

class GetAllFavoritesUseCase(
    private val favoritesRepository: FavoritesRepository
) {
    suspend operator fun invoke(userId: Int, categoryId: Int) =
        favoritesRepository.getAllFavorites(userId = userId, categoryId = categoryId)

}