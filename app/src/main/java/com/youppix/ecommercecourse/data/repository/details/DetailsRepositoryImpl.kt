package com.youppix.ecommercecourse.data.repository.details

import com.youppix.ecommercecourse.common.Resource
import com.youppix.ecommercecourse.data.remote.details.DetailsService
import com.youppix.ecommercecourse.data.remote.details.dto.DetailsResponse
import com.youppix.ecommercecourse.domain.repository.details.DetailsRepository
import kotlinx.coroutines.flow.Flow

class DetailsRepositoryImpl(private val detailsService : DetailsService) : DetailsRepository {
    override suspend fun getItemsDetails(itemId : Int , categoryId : Int): Flow<Resource<DetailsResponse>> {
        return detailsService.getItemDetails(itemId , categoryId)
    }
}