package com.youppix.ecommercecourse.domain.model.address

import androidx.compose.runtime.Immutable
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Immutable
@Serializable
data class Address(
    @SerialName("user_id")
    val userId : Int = 0,
    @SerialName("address_id")
    val addressId: Int = 0,
    @SerialName("address_name")
    val addressName: String = "",
    @SerialName("address_wilaya")
    val addressWilaya: Wilaya ?= null ,
    @SerialName("address_commune")
    val addressCommune: Commune ?= null,
    @SerialName("address_specific")
    val addressSpecific: String = "",
    @SerialName("address_code_postal")
    val addressCodePostal: String = "",
    @SerialName("address_default")
    val addressDefault : Int = 0
)
