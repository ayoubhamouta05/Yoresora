package com.youppix.ecommercecourse.domain.useCases.cart

import com.youppix.ecommercecourse.domain.repository.cart.CartRepository

class GetCartsUseCase(
    private val cartRepository: CartRepository
)  {

    suspend operator fun invoke(userId: Int) =
        cartRepository.getCarts(userId)


}