package com.youppix.ecommercecourse.data.repository.home

import com.youppix.ecommercecourse.common.Resource
import com.youppix.ecommercecourse.data.remote.home.HomeService
import com.youppix.ecommercecourse.data.remote.home.dto.HomeResponse
import com.youppix.ecommercecourse.data.remote.home.dto.ItemsResponse
import com.youppix.ecommercecourse.domain.model.categories.Category
import com.youppix.ecommercecourse.domain.repository.home.HomeRepository
import kotlinx.coroutines.flow.Flow

class HomeRepositoryImpl(private val homeService: HomeService) : HomeRepository {
    override suspend fun getHomeData(): Flow<Resource<HomeResponse>> {
        return homeService.getHomeData()
    }

    override suspend fun getAllItems(): Flow<Resource<ItemsResponse>> {
        return homeService.getAllItems()
    }

    override suspend fun getItemOfCategory(category: Int): Flow<Resource<ItemsResponse>> {
        return homeService.getItemsByCategory(category)
    }
}