package com.youppix.ecommercecourse.data.repository.favorites

import com.youppix.ecommercecourse.common.Resource
import com.youppix.ecommercecourse.data.remote.auth.dto.AuthResponse
import com.youppix.ecommercecourse.data.remote.favorites.FavoritesService
import com.youppix.ecommercecourse.data.remote.home.dto.ItemsResponse
import com.youppix.ecommercecourse.domain.model.categories.CategoryData
import com.youppix.ecommercecourse.domain.repository.favorites.FavoritesRepository
import kotlinx.coroutines.flow.Flow

class FavoritesRepositoryImpl(private val favoritesService: FavoritesService) : FavoritesRepository
{
    override suspend fun getAllFavorites(
        userId: Int,
        categoryId: Int
    ): Flow<Resource<ItemsResponse>> {
        return favoritesService.getAllFavorites(userId, categoryId)
    }

    override suspend fun getAllCategories(): Flow<Resource<List<CategoryData>>> {
        return favoritesService.getAllCategories()
    }

    override suspend fun addOrDeleteFavorite(
        itemId: Int,
        userId: Int
    ): Flow<Resource<AuthResponse>> {
        return favoritesService.addOrDeleteFavorite(itemId = itemId , userId =  userId)
    }
}