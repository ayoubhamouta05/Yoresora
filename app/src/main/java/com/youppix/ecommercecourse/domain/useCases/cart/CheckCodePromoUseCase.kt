package com.youppix.ecommercecourse.domain.useCases.cart

import com.youppix.ecommercecourse.domain.repository.cart.CartRepository

class CheckCodePromoUseCase(
    private val cartRepository: CartRepository,
) {
    suspend operator fun invoke(codePromo: String) =
        cartRepository.checkCodePromo(codePromo)
}