package com.youppix.ecommercecourse.domain.useCases.details

import com.youppix.ecommercecourse.domain.model.details.CustomSize
import com.youppix.ecommercecourse.domain.repository.details.DetailsRepository

class UpsertCustomSizeUseCase(
    private val detailsRepository: DetailsRepository
) {
    suspend operator fun invoke(customSize: CustomSize) =
        detailsRepository.upsertCustomSize(customSize)

}