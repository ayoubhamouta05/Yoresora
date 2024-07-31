package com.youppix.ecommercecourse.domain.model.items

import androidx.compose.runtime.Immutable


@Immutable
data class Item(
    val itemId : Int,
    val itemColor: String,
    val itemDesc: String,
    val itemDescAr: String,
    val itemDiscount: Int,
    val itemImage: String,
    val itemName: String,
    val itemNameAr: String,
    val itemPrice: Int
)


fun ItemData.toItem(): Item {
    return Item(
        itemId= items_id,
        itemColor = items_color,
        itemDesc = items_desc,
        itemDescAr = items_desc_ar,
        itemDiscount = items_discount,
        itemImage = items_image,
        itemName = items_name,
        itemNameAr = items_name_ar,
        itemPrice = items_price,
    )
}

fun List<ItemData>.toItems() : List<Item>{
    return this.map { it.toItem() }
}