package com.youppix.ecommercecourse.domain.repository.home

import com.youppix.ecommercecourse.common.Resource
import com.youppix.ecommercecourse.data.remote.home.dto.HomeResponse
import com.youppix.ecommercecourse.data.remote.home.dto.ItemsResponse
import kotlinx.coroutines.flow.Flow

interface HomeRepository {

    suspend fun getHomeData() : Flow<Resource<HomeResponse>>

    suspend fun getAllItems() : Flow<Resource<ItemsResponse>>

    suspend fun getItemOfCategory(category : Int) : Flow<Resource<ItemsResponse>>
}