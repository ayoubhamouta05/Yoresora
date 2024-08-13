package com.youppix.ecommercecourse.domain.useCases.details

import com.youppix.ecommercecourse.domain.repository.details.DetailsRepository

class CheckSizeExistenceUseCase(
    private val detailsRepository: DetailsRepository
) {

    suspend operator fun invoke(userId : Int) =
        detailsRepository.checkSizeExistence(userId)

}