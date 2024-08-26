package com.youppix.ecommercecourse.domain.useCases.cart

import com.youppix.ecommercecourse.domain.repository.cart.CartRepository

class UpdateQuantityUseCase(
    private val cartRepository: CartRepository,
) {

    suspend operator fun invoke(
        userId: Int,
        itemId: Int,
        itemSize: Int,
        itemColor: Int,
        itemQuantity: Int,
    ) = cartRepository.updateQuantity(
        userId = userId,
        itemId = itemId,
        itemSize = itemSize,
        itemColor = itemColor,
        itemQuantity = itemQuantity
    )

}