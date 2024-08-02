package com.youppix.ecommercecourse.domain.useCases.search

import com.youppix.ecommercecourse.common.Resource
import com.youppix.ecommercecourse.data.remote.home.dto.ItemsResponse
import com.youppix.ecommercecourse.domain.model.items.FilteringItems
import com.youppix.ecommercecourse.domain.repository.search.SearchRepository
import kotlinx.coroutines.flow.Flow

data class GetItemsByFilteringUseCase(
    private val searchRepository: SearchRepository
) {

    suspend operator fun invoke(filteringItems: FilteringItems): Flow<Resource<ItemsResponse>> {
        return searchRepository.getItemsByFiltering(filteringItems)
    }


}