package com.youppix.ecommercecourse.domain.useCases.address

import com.youppix.ecommercecourse.domain.model.address.Address
import com.youppix.ecommercecourse.domain.repository.address.AddressRepository

class UpsertAddressUseCase(
    private val addressRepository: AddressRepository
) {

    suspend operator fun invoke (address : Address)  =
        addressRepository.upsertAddress(address)
}