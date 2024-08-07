package com.youppix.ecommercecourse.domain.model.items

import kotlinx.serialization.Serializable

@Serializable
data class ItemData(
    val items_active: Int,
    val items_cat: Int,
    val items_count: Int,
    val items_date: String,
    val items_desc: String,
    val items_desc_ar: String,
    val items_discount: Int,
    val items_id: Int,
    val items_image: String,
    val items_name: String,
    val items_name_ar: String,
    val items_price: Int
)