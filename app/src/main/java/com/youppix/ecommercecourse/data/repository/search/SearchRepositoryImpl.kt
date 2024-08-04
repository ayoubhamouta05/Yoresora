package com.youppix.ecommercecourse.data.repository.search

import com.youppix.ecommercecourse.common.Resource
import com.youppix.ecommercecourse.data.remote.home.HomeService
import com.youppix.ecommercecourse.data.remote.home.dto.ItemsResponse
import com.youppix.ecommercecourse.domain.model.categories.CategoryData
import com.youppix.ecommercecourse.domain.model.items.ColorData
import com.youppix.ecommercecourse.domain.model.items.FilteringItems
import com.youppix.ecommercecourse.domain.repository.search.SearchRepository
import kotlinx.coroutines.flow.Flow

class SearchRepositoryImpl(private val homeService: HomeService) : SearchRepository {
    override suspend fun getAllCategories(): Flow<Resource<List<CategoryData>>> {
        return homeService.getAllCategories()
    }

    override suspend fun getItemsByFiltering(filteringItems: FilteringItems): Flow<Resource<ItemsResponse>> {
        return homeService.getItemsByFiltering(filteringItems)
    }

    override suspend fun getAllColors(): Flow<Resource<List<ColorData>>> {
        return homeService.getAllColors()
    }


}