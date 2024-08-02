package com.youppix.ecommercecourse.domain.model.items

import kotlinx.serialization.Serializable

@Serializable
data class FilteringItems(

    val itemsCat: Int? = null,
    val initialPrice: Int = 0,
    val finalPrice: Int = Int.MAX_VALUE,
    val itemsName: String = "",
    val itemNameAr: String = "",
    val initialDiscount: Int = 0,
    val finalDiscount: Int = 100

)
