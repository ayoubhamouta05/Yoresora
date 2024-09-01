package com.youppix.ecommercecourse.presentation.home_app.address

import com.youppix.ecommercecourse.domain.model.address.Address
import com.youppix.ecommercecourse.domain.model.address.Commune
import com.youppix.ecommercecourse.domain.model.address.Wilaya

sealed class AddressEvent {
    data class GetAllAddress(val userId : Int) : AddressEvent()
    data class  ToggleShowBottomSheet(val isInserting : Boolean?= null) : AddressEvent()
    data class UpsertAddress(val address : Address) : AddressEvent()
    data class UpdateAddressCodePostal(val codePostal : String) : AddressEvent()
    data class UpdateAddressName (val name : String) : AddressEvent()
    data object ToggleWilayaDropMenu  : AddressEvent()
    data object ToggleCommuneDropMenu  : AddressEvent()
    data class DeleteAddress(val addressId : Int, val userId: Int) : AddressEvent()
    data class ToggleSelectedAddressId (val address : Address ?=null) : AddressEvent()
    data class GetCommune(val wilayaId : Int) : AddressEvent()
    data class SetCommune(val commune: Commune) : AddressEvent()
    data class SetWilaya(val wilaya : Wilaya) : AddressEvent()
    data class UpdateAddressDefault(val value : Boolean) : AddressEvent()
    data class UpdateDefaultAddressIndex(val index: Int) : AddressEvent()
}