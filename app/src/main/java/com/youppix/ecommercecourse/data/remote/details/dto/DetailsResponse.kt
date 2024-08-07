package com.youppix.ecommercecourse.data.remote.details.dto

import androidx.compose.runtime.Immutable
import com.youppix.ecommercecourse.domain.model.details.DetailsData
import kotlinx.serialization.Serializable

@Immutable
@Serializable
data class DetailsResponse(
    val data: DetailsData ?= null,
    val message: String ="",
    val status: String
)