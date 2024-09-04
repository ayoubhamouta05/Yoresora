package com.youppix.ecommercecourse.domain.useCases.address

import com.youppix.ecommercecourse.domain.model.address.Address
import com.youppix.ecommercecourse.domain.repository.address.AddressRepository

class UpsertAddressUseCase(
    private val addressRepository: AddressRepository
) {

    suspend operator fun invoke (address : Address ,userCustomerId : String , isArabic : Boolean)  =
        addressRepository.upsertAddress(address , userCustomerId , isArabic)
}