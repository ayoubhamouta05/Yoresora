package com.youppix.ecommercecourse.domain.model.items

import androidx.compose.runtime.Immutable
import java.io.Serializable


@Immutable
data class Item(
    val itemId : Int = 0,
    val itemCat : Int = 0,
    val itemDesc: String ="",
    val itemDescAr: String ="",
    val itemDiscount: Int = 0,
    val itemImage: String = "",
    val itemName: String = "",
    val itemNameAr: String = "",
    val itemPrice: Int = 0
) : Serializable


fun ItemData.toItem(): Item {
    return Item(
        itemId= items_id,
        itemCat = items_cat,

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