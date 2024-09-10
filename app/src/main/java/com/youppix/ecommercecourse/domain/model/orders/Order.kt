package com.youppix.ecommercecourse.domain.model.orders

import kotlinx.serialization.Serializable

@Serializable
data class Order(
    val deliveryMethod: Int,
    val ordersStatus : String ,
    val numberOfItems: String,
    val ordersDescription : String,
    val ordersAmount: Int,
    val ordersDate: Long,
    val ordersId : String
)
