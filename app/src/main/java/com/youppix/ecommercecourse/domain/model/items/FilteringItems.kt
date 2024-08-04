package com.youppix.ecommercecourse.domain.model.items

import java.io.Serializable


data class FilteringItems(
    val itemsCat: Int? = null,
    val initialPrice: Int = 0,
    val finalPrice: Int = Int.MAX_VALUE,
    val maxPrice: Int = Int.MAX_VALUE,
    val minPrice : Int = 0,
    val itemsName: String = "",
    val initialDiscount: Int = 0,
    val finalDiscount: Int = 100 ,
    val colors : ArrayList<ColorData> = arrayListOf()
) : Serializable
