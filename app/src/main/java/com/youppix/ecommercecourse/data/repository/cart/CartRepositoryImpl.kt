package com.youppix.ecommercecourse.data.repository.cart

import com.youppix.ecommercecourse.common.Resource
import com.youppix.ecommercecourse.data.remote.auth.dto.AuthResponse
import com.youppix.ecommercecourse.data.remote.cart.CartService
import com.youppix.ecommercecourse.data.remote.cart.dto.CartResponse
import com.youppix.ecommercecourse.domain.repository.cart.CartRepository
import kotlinx.coroutines.flow.Flow

class CartRepositoryImpl(private val cartService: CartService) : CartRepository {
    override suspend fun getCarts(userId: Int): Flow<Resource<CartResponse>> {
        return cartService.getCarts(userId)
    }

    override suspend fun addOrDeleteCart(
        itemId: Int,
        userId: Int,
        itemSize: Int,
        itemColor: Int,
    ): Flow<Resource<AuthResponse>> {
        return cartService.addOrDeleteCart(itemId, userId, itemSize, itemColor)
    }
}