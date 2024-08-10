package com.youppix.ecommercecourse.domain.model.details

import kotlinx.serialization.Serializable

@Serializable
data class CustomSizeData(
    val arm_circumference: Int,
    val buttocks_circumference: Int,
    val chest_circumference: Int,
    val custom_size_name: String,
    val custom_sizes_id: Int,
    val desired_arm_length: Int,
    val shoulder_width: Int,
    val total_length: Int,
    val user_id: Int,
    val waistline: Int,
    val wrist_circumference: Int
)


fun CustomSizeData.toCustomSize(): CustomSize {
    return CustomSize(
        armCircumference = arm_circumference.toString(),
        buttocksCircumference = buttocks_circumference.toString(),
        chestCircumference = chest_circumference.toString(),
        customSizeName = custom_size_name,
        desiredArmLength = desired_arm_length.toString(),
        shoulderWidth = shoulder_width.toString(),
        totalLength = total_length.toFloat(),
        userId = user_id,
        waistLine = waistline.toString(),
        wristCircumference = wrist_circumference.toString()
    )
}