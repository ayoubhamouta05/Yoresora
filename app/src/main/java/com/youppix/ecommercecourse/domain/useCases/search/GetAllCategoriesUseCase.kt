package com.youppix.ecommercecourse.domain.useCases.search

import com.youppix.ecommercecourse.common.Resource
import com.youppix.ecommercecourse.domain.model.categories.CategoryData
import com.youppix.ecommercecourse.domain.repository.search.SearchRepository
import kotlinx.coroutines.flow.Flow

class GetAllCategoriesUseCase(
    private val searchRepository: SearchRepository
) {

    suspend operator fun invoke(): Flow<Resource<List<CategoryData>>> {
        return searchRepository.getAllCategories()
    }


}