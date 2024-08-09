package com.youppix.ecommercecourse.domain.useCases.favorites

import com.youppix.ecommercecourse.common.Resource
import com.youppix.ecommercecourse.domain.model.categories.CategoryData
import com.youppix.ecommercecourse.domain.repository.favorites.FavoritesRepository
import kotlinx.coroutines.flow.Flow

class GetAllCategoriesUseCase(
    private val favoritesRepository: FavoritesRepository
) {
    suspend operator fun invoke(): Flow<Resource<List<CategoryData>>> {
        return favoritesRepository.getAllCategories()
    }

}