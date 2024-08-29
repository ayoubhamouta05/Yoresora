package com.youppix.ecommercecourse.data.remote.profile.dto

import com.youppix.ecommercecourse.domain.model.address.Address
import kotlinx.serialization.Serializable

@Serializable
data class AddressResponse (
    val status : String ,
    val message : String ,
    val data : List<Address>?= null
)
