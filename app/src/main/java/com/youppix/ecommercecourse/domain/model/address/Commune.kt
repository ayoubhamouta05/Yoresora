package com.youppix.ecommercecourse.domain.model.address

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Commune(
    @SerialName("communes_id")
    val communeId : Int,
    @SerialName("wilaya_id")
    val wilayaId : Int,
    @SerialName("communes_name")
    val communeName : String,
    @SerialName("communes_name_ar")
    val communeNameAr :String
)
