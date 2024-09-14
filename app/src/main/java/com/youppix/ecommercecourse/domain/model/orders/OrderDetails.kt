package com.youppix.ecommercecourse.domain.model.orders

import com.youppix.ecommercecourse.domain.model.cart.CartData
import kotlinx.serialization.Serializable

@Serializable
data class OrderDetails(
    val colors_name: String,
    val colors_name_ar: String,
    val item_color: Int,
    val item_id: Int,
    val item_quantity: Int,
    val item_size: Int,
    val items_desc: String,
    val items_desc_ar: String,
    val items_discount: Int,
    val items_image: String,
    val items_name: String,
    val items_name_ar: String,
    val items_price: Int,
    val orders_id: String,
    val sizes_name: String
)


fun OrderDetails.toCartData(): CartData {
    return CartData(
        cart_id = 0,
        colors_name = colors_name,
        colors_name_ar = colors_name_ar,
        item_color = item_color,
        items_image = items_image,
        items_desc = items_desc,
        items_desc_ar = items_desc_ar,
        items_discount = items_discount,
        item_id = item_id,
        item_quantity = item_quantity,
        item_size = item_size,
        items_name = items_name,
        items_name_ar = items_name_ar,
        items_price = items_price,
        sizes_name = sizes_name,
        user_id = 0
    )
}