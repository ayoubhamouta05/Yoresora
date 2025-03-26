package com.youppix.ecommercecourse.data.remote.cart.dto

import kotlinx.serialization.Serializable

@Serializable
data class CheckPromoCodeResponse(
    val status : String ,
    val message : String ,
    val item_id : Int? = null,
    val item_price : Int = 0 ,
    val item_quantity : Int = 0 ,
    val promo_percent : Int = 0
)
