package com.youppix.ecommercecourse.domain.model.address

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Wilaya(
    @SerialName("wilayas_id")
    val wilayaId : Int,
    @SerialName("wilayas_name")
    val wilayaName : String,
    @SerialName("wilayas_name_ar")
    val wilayaNameAr :String
)
