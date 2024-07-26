package com.youppix.ecommercecourse.data.repository.home

import com.youppix.ecommercecourse.common.Resource
import com.youppix.ecommercecourse.data.remote.home.HomeService
import com.youppix.ecommercecourse.data.remote.home.dto.HomeResponse
import com.youppix.ecommercecourse.domain.model.categories.Category
import com.youppix.ecommercecourse.domain.repository.home.HomeRepository
import kotlinx.coroutines.flow.Flow

class HomeRepositoryImpl(private val homeService: HomeService) : HomeRepository {
    override suspend fun getCategories(): Flow<Resource<HomeResponse>> {
        return homeService.getHomeData()
    }
}