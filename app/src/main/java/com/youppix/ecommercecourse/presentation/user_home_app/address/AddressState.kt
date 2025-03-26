package com.youppix.ecommercecourse.presentation.user_home_app.address

import androidx.compose.runtime.Immutable
import com.youppix.ecommercecourse.domain.model.address.Address
import com.youppix.ecommercecourse.domain.model.address.Commune
import com.youppix.ecommercecourse.domain.model.address.Wilaya

@Immutable
data class AddressState(
    val isLoading: Boolean = false,
    val items: List<Address> = emptyList(),
    val communeList: List<Commune> = emptyList(),
    val wilayaList : List<Wilaya> = emptyList(),
    val showBottomSheet: Boolean = false,
    val isInserting: Boolean = true,
    val selectedAddress: Address=Address(),
    val defaultAddressIndex : Int = 0,
    val addressNameError: String? = null,
    val dropWilayaMenu: Boolean = false,
    val dropCommuneMenu : Boolean = false,
    val specificAddressError: String?=null,
    val codePostalError : String?=null,
    val wilayaError : String?=null ,
    val communeError : String?=null,
    val success: Boolean = false,
    val selectAddressSuccess : Boolean = false,
    val error: String? = null,
)
