package com.youppix.ecommercecourse.domain.repository.cart

import com.youppix.ecommercecourse.common.Resource
import com.youppix.ecommercecourse.data.remote.auth.dto.AuthResponse
import com.youppix.ecommercecourse.data.remote.cart.dto.CartResponse
import kotlinx.coroutines.flow.Flow

interface CartRepository {

    suspend fun getCartItems(userId: Int): Flow<Resource<CartResponse>>
    suspend fun updateQuantity(
        userId: Int, itemId: Int,
        itemSize: Int,
        itemColor: Int,
        itemQuantity: Int,
    ): Flow<Resource<AuthResponse>>
}