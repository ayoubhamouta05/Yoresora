package com.youppix.ecommercecourse.domain.repository.details

import com.youppix.ecommercecourse.common.Resource
import com.youppix.ecommercecourse.data.remote.auth.dto.AuthResponse
import com.youppix.ecommercecourse.data.remote.details.dto.DetailsResponse
import kotlinx.coroutines.flow.Flow

interface DetailsRepository {
    suspend fun getItemsDetails(itemId: Int, categoryId: Int , userId: Int): Flow<Resource<DetailsResponse>>

    suspend fun addOrDeleteFromFavorite(userId: Int, itemId: Int): Flow<Resource<AuthResponse>>
}