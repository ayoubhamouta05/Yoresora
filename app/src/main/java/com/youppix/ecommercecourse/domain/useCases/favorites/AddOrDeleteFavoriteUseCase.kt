package com.youppix.ecommercecourse.domain.useCases.favorites

import com.youppix.ecommercecourse.domain.repository.favorites.FavoritesRepository

class AddOrDeleteFavoriteUseCase(
    private val favoritesRepository: FavoritesRepository
) {

    suspend operator fun invoke(userId: Int, itemId: Int) =
        favoritesRepository.addOrDeleteFavorite(userId = userId, itemId = itemId)

}