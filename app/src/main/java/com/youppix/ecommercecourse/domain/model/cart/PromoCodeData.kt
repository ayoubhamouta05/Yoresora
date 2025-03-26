package com.youppix.ecommercecourse.domain.model.cart

data class PromoCodeData(
    val promoCode : String ,
    val itemId : Int ,
    val itemQuantity : Int ,
    val itemPrice : Int ,
    val percent : Int
)
