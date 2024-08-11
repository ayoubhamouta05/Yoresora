package com.youppix.ecommercecourse.domain.model.details

import kotlinx.serialization.Serializable

@Serializable
data class CustomSizeData(
    val arm_circumference: Float = 0f,
    val buttocks_circumference: Float = 0f,
    val buttocks_height : Float = 0f,
    val chest_circumference: Float = 0f,
    val chest_height : Float = 0f,
    val custom_size_name: String="",
    val custom_sizes_id: Int? = null,
    val desired_arm_length: Float = 0f,
    val shoulder_width: Float = 0f,
    val total_length: Float = 0f,
    val user_id: Int?=null,
    val waistline: Float = 0f,
    val wrist_circumference: Float = 0f
)



fun CustomSizeData.toCustomSize(): CustomSize {
    return CustomSize(
        armCircumference = arm_circumference.toString(),
        buttocksCircumference = buttocks_circumference.toString(),
        chestCircumference = chest_circumference.toString(),
        chestHeight = chest_height.toString(),
        buttocksHeight = buttocks_height.toString(),
        customSizeName = custom_size_name,
        desiredArmLength = desired_arm_length.toString(),
        shoulderWidth = shoulder_width.toString(),
        totalLength = total_length,
        userId = user_id,
        waistLine = waistline.toString(),
        wristCircumference = wrist_circumference.toString()
    )
}