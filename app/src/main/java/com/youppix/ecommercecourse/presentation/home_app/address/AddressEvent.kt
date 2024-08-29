package com.youppix.ecommercecourse.presentation.home_app.address

import com.youppix.ecommercecourse.domain.model.address.Address

sealed class AddressEvent {
    data class GetAllAddress(val userId : Int) : AddressEvent()
    data class  ToggleShowBottomSheet(val isInserting : Boolean?= null) : AddressEvent()
    data class UpsertAddress(val address : Address) : AddressEvent()
    data class DeleteAddress(val addressId : Int, val userId: Int) : AddressEvent()
    data class ToggleSelectedAddressId (val address : Address ?=null) : AddressEvent()
}