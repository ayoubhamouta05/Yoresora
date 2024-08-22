package com.youppix.ecommercecourse.domain.model

import com.youppix.ecommercecourse.domain.model.items.ColorData
import com.youppix.ecommercecourse.domain.model.items.Item

data class CartItemData(
    val itemId : Int = 0,
    val itemCat : Int = 0,
    val itemDesc: String ="",
    val itemDescAr: String ="",
    val itemDiscount: Int = 0,
    val itemImage: String = "",
    val itemName: String = "",
    val itemNameAr: String = "",
    val itemPrice: Int = 0,
    val quantity : Int ,
    val itemColor : ColorData ,
    val itemSize : String
)


fun CartItemData.toItem() : Item{
    return Item(
        itemId = this.itemId ,
        itemCat = this.itemCat ,
        itemDesc = this.itemDesc ,
        itemDescAr = this.itemDescAr ,
        itemDiscount = this.itemDiscount ,
        itemImage = this.itemImage ,
        itemName = this.itemName ,
        itemNameAr = this.itemNameAr ,
        itemPrice = this.itemPrice
    )
}
