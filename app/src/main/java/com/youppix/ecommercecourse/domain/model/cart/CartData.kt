package com.youppix.ecommercecourse.domain.model.cart

import androidx.compose.runtime.Immutable
import com.youppix.ecommercecourse.domain.model.items.Item
import kotlinx.serialization.Serializable

@Immutable
@Serializable
data class CartData(
    val cart_id: Int,
    val colors_name: String,
    val colors_name_ar: String,
    val item_color: Int,
    val items_image : String,
    val items_desc : String,
    val items_desc_ar :String,
    val item_id: Int,
    var item_quantity: Int,
    val item_size: Int,
    val items_name: String,
    val items_name_ar : String,
    val items_price: Int,
    val sizes_name: String,
    val user_id: Int
)

fun CartData.toItem() =
    Item(
        itemId = this.item_id ,
        itemName = this.items_name ,
        itemImage = this.items_image ,
        itemNameAr = this.items_name_ar,
        itemPrice = this.items_price,
        itemDesc = this.items_desc,
        itemDescAr = this.items_desc_ar
    )