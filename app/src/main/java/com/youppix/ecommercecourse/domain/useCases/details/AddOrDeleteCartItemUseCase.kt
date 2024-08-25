package com.youppix.ecommercecourse.domain.useCases.details

import com.youppix.ecommercecourse.domain.repository.details.DetailsRepository

class AddOrDeleteCartItemUseCase(
    private val detailsRepository: DetailsRepository,
) {

    suspend operator fun invoke(
        itemId: Int,
        userId: Int,
        itemSize: Int,
        itemColor: Int,
    )
            = detailsRepository.addOrDeleteCartItem(itemId, userId, itemSize, itemColor)

}