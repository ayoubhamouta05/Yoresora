package com.youppix.ecommercecourse.domain.useCases.address

data class AddressUseCases (
    val getAllAddress : GetAllAddressUseCase,
    val upsertAddress: UpsertAddressUseCase ,
    val deleteAddress : DeleteAddressUseCase
)