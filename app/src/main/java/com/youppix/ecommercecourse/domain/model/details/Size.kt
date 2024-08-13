package com.youppix.ecommercecourse.domain.model.details

import java.io.Serializable


@kotlinx.serialization.Serializable
data class Size(
    val userId: Int? = null,
    val sizeId : Int ?=null,
    val sizeName: String = "",
    val shoulderWidth: String = "0.0",
    val chestCircumference: String = "0.0",
    val chestHeight: String = "0.0",
    val waistLine: String = "0.0",
    val buttocksCircumference: String = "0.0",
    val buttocksHeight: String = "0.0",
    val armCircumference: String = "0.0",
    val wristCircumference: String = "0.0",
    val desiredArmLength: String = "0.0",
    val totalLength: Float = 150f
) : Serializable