package com.youppix.ecommercecourse.data.repository.cart

import com.youppix.ecommercecourse.common.Resource
import com.youppix.ecommercecourse.data.remote.cart.CartService
import com.youppix.ecommercecourse.data.remote.cart.dto.CartResponse
import com.youppix.ecommercecourse.domain.repository.cart.CartRepository
import kotlinx.coroutines.flow.Flow

class CartRepositoryImpl(private val cartService: CartService) : CartRepository {
    override suspend fun getCartItems(userId: Int): Flow<Resource<CartResponse>> {
        return cartService.getCartItems(userId)
    }


}