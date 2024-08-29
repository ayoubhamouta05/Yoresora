package com.youppix.ecommercecourse.domain.useCases.address

import com.youppix.ecommercecourse.domain.repository.address.AddressRepository

class GetAllAddressUseCase(
    private val addressRepository: AddressRepository,
) {
    suspend operator fun invoke(userId: Int) =
        addressRepository.getAllAddress(userId)

}