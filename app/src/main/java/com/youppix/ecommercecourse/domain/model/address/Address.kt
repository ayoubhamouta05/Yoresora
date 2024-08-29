package com.youppix.ecommercecourse.domain.model.address

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class Address(
    @SerialName("user_id")
    val userId : Int = 0,
    @SerialName("address_id")
    val addressId: Int = 0,
    @SerialName("address_name")
    val addressName: String = "",
    @SerialName("address_wilaya")
    val addressWilaya: String = "",
    @SerialName("address_commune")
    val addressCommune: String = "",
    @SerialName("address_code_postal")
    val addressCodePostal: String = "",
    @SerialName("address_default")
    val addressDefault : Int = 0
)
