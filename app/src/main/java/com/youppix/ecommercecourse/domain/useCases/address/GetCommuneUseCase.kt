package com.youppix.ecommercecourse.domain.useCases.address

import com.youppix.ecommercecourse.domain.repository.address.AddressRepository

class GetCommuneUseCase(
    private val addressRepository: AddressRepository
) {

    suspend operator fun invoke(wilayaId : Int) =
        addressRepository.getCommune(wilayaId)

}