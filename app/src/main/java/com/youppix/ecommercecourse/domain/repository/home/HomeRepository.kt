package com.youppix.ecommercecourse.domain.repository.home

import com.youppix.ecommercecourse.common.Resource
import com.youppix.ecommercecourse.data.remote.home.dto.HomeResponse
import com.youppix.ecommercecourse.domain.model.categories.Category
import kotlinx.coroutines.flow.Flow

interface HomeRepository {

    suspend fun getCategories() : Flow<Resource<HomeResponse>>

}