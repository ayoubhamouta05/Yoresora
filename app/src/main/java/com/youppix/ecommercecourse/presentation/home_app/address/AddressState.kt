package com.youppix.ecommercecourse.presentation.home_app.address

import androidx.compose.runtime.Immutable
import com.youppix.ecommercecourse.domain.model.address.Address

@Immutable
data class AddressState(
    val isLoading: Boolean = false,
    val items: List<Address> = emptyList(),
    val showBottomSheet: Boolean = false,
    val isInserting: Boolean = true,
    val selectedAddress: Address=Address(),
    val addressNameError: String? = null,
    val addressWilayaError: String? = null,
    val addressCommuneError: String? = null,
    val addressCodePostalError: String? = null,
    val success: Boolean = false,
    val error: String? = null,
)
