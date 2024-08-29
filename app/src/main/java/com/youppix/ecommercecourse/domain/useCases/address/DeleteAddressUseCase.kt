package com.youppix.ecommercecourse.domain.useCases.address

import com.youppix.ecommercecourse.domain.repository.address.AddressRepository

class DeleteAddressUseCase(
    private val addressRepository: AddressRepository,
) {

    suspend operator fun invoke(addressId: Int, userId: Int) =
        addressRepository.deleteAddress(addressId, userId)
}