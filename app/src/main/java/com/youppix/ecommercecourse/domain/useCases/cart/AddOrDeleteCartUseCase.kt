package com.youppix.ecommercecourse.domain.useCases.cart

import com.youppix.ecommercecourse.domain.repository.cart.CartRepository

class AddOrDeleteCartUseCase(
    private val cartRepository: CartRepository,
) {

    suspend operator fun invoke(
        itemId: Int,
        userId: Int,
        itemSize: Int,
        itemColor: Int,
    )
            = cartRepository.addOrDeleteCart(itemId, userId, itemSize, itemColor)

}