package com.youppix.ecommercecourse.domain.useCases.details

import com.youppix.ecommercecourse.domain.repository.details.DetailsRepository

class AddOrDeleteFromFavoriteUseCase
    (
    private val detailsRepository: DetailsRepository
) {

    suspend operator fun invoke(userId: Int, itemId: Int) =
        detailsRepository.addOrDeleteFromFavorite(userId, itemId)

}
