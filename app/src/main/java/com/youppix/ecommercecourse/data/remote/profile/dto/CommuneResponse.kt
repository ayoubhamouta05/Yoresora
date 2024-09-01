package com.youppix.ecommercecourse.data.remote.profile.dto

import com.youppix.ecommercecourse.domain.model.address.Commune
import kotlinx.serialization.Serializable

@Serializable
data class CommuneResponse(
    val status : String ,
    val message : String ,
    val data : List<Commune>? = null
)