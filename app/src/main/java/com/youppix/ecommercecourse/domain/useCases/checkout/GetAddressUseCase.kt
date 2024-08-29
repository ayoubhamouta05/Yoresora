package com.youppix.ecommercecourse.domain.useCases.checkout

import com.youppix.ecommercecourse.domain.repository.checkout.CheckoutRepository

class GetAddressUseCase(
    private val checkoutRepository: CheckoutRepository,
) {

    suspend operator fun invoke(userId: Int) =
        checkoutRepository.getAddress(userId)

}
