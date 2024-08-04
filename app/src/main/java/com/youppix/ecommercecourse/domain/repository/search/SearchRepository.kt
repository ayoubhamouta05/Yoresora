package com.youppix.ecommercecourse.domain.repository.search

import com.youppix.ecommercecourse.common.Resource
import com.youppix.ecommercecourse.data.remote.home.dto.ItemsResponse
import com.youppix.ecommercecourse.domain.model.categories.CategoryData
import com.youppix.ecommercecourse.domain.model.items.ColorData
import com.youppix.ecommercecourse.domain.model.items.FilteringItems
import kotlinx.coroutines.flow.Flow

interface SearchRepository {

    suspend fun getAllCategories(): Flow<Resource<List<CategoryData>>>

    suspend fun getItemsByFiltering(filteringItems: FilteringItems): Flow<Resource<ItemsResponse>>

    suspend fun getAllColors() : Flow<Resource<List<ColorData>>>

}