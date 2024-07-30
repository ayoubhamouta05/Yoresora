package com.youppix.ecommercecourse.domain.useCases.home

import com.youppix.ecommercecourse.common.Resource
import com.youppix.ecommercecourse.data.remote.home.dto.ItemsResponse
import com.youppix.ecommercecourse.domain.repository.home.HomeRepository
import kotlinx.coroutines.flow.Flow

class GetAllItemsUseCase (
    private val homeRepository: HomeRepository
) {
    suspend operator fun invoke() : Flow<Resource<ItemsResponse>>{
        return homeRepository.getAllItems()
    }

}