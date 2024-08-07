package com.youppix.ecommercecourse.domain.useCases.details

import com.youppix.ecommercecourse.domain.repository.details.DetailsRepository

class GetItemDetailsUseCase(
    private val detailsRepository: DetailsRepository
) {

    suspend operator fun invoke(itemId: Int, categoryId: Int) =
        detailsRepository.getItemsDetails(itemId, categoryId)

}