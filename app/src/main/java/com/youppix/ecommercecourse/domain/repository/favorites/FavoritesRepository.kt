package com.youppix.ecommercecourse.domain.repository.favorites

import com.youppix.ecommercecourse.common.Resource
import com.youppix.ecommercecourse.data.remote.auth.dto.AuthResponse
import com.youppix.ecommercecourse.data.remote.home.dto.ItemsResponse
import com.youppix.ecommercecourse.domain.model.categories.CategoryData
import kotlinx.coroutines.flow.Flow

interface FavoritesRepository {

    suspend fun getAllFavorites(userId : Int , categoryId : Int) : Flow<Resource<ItemsResponse>>

    suspend fun getAllCategories(): Flow<Resource<List<CategoryData>>>

    suspend fun addOrDeleteFavorite(itemId : Int ,userId : Int): Flow<Resource<AuthResponse>>


}