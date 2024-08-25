package com.youppix.ecommercecourse.domain.repository.details

import com.youppix.ecommercecourse.common.Resource
import com.youppix.ecommercecourse.data.remote.auth.dto.AuthResponse
import com.youppix.ecommercecourse.data.remote.details.dto.DetailsResponse
import com.youppix.ecommercecourse.domain.model.details.Size
import kotlinx.coroutines.flow.Flow

interface DetailsRepository {
    suspend fun getItemsDetails(itemId: Int, userId: Int): Flow<Resource<DetailsResponse>>

    suspend fun addOrDeleteFromFavorite(userId: Int, itemId: Int): Flow<Resource<AuthResponse>>

    suspend fun checkSizeExistence(userId: Int) : Flow<Resource<AuthResponse>>

    suspend fun upsertCustomSize(size: Size) : Flow<Resource<AuthResponse>>

    suspend fun addOrDeleteCartItem(
        itemId: Int,
        userId: Int,
        itemSize: Int,
        itemColor: Int,
    ): Flow<Resource<AuthResponse>>
}