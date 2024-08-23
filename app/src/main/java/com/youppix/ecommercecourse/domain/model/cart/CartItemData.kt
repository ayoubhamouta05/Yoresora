package com.youppix.ecommercecourse.domain.model.cart

import com.youppix.ecommercecourse.domain.model.items.ColorData
import com.youppix.ecommercecourse.domain.model.items.Item

data class CartItemData(
    val item : Item = Item() ,
    val quantity : Int ,
    val itemColor : ColorData,
    val itemSize : String
)
