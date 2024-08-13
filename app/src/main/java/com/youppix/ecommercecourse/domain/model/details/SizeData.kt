package com.youppix.ecommercecourse.domain.model.details

import kotlinx.serialization.Serializable

@Serializable
data class SizeData(
    val sizes_id: Int,
    val sizes_name: String = "",
    val sizes_shoulder_width: Float = 0f,
    val sizes_chest_circumference: Float = 0f,
    val sizes_chest_height: Float = 0f,
    val sizes_waistline: Float = 0f,
    val sizes_buttocks_circumference: Float = 0f,
    val sizes_buttocks_height: Float = 0f,
    val sizes_arm_circumference: Float = 0f,
    val sizes_wrist_circumference: Float = 0f,
    val sizes_desired_arm_length: Float = 0f,
    val sizes_total_length: Float = 0f
)


fun SizeData.toSize(): Size {
    return Size(
        sizeId = sizes_id,
        sizeName = sizes_name,
        shoulderWidth = sizes_shoulder_width.toString(),
        chestCircumference = sizes_chest_circumference.toString(),
        chestHeight = sizes_chest_height.toString(),
        waistLine = sizes_waistline.toString(),
        buttocksCircumference = sizes_buttocks_circumference.toString(),
        buttocksHeight = sizes_buttocks_height.toString(),
        armCircumference = sizes_arm_circumference.toString(),
        wristCircumference = sizes_wrist_circumference.toString(),
        desiredArmLength = sizes_desired_arm_length.toString(),
        totalLength = sizes_total_length
    )
}

fun List<SizeData>.toSizes(): List<Size> {
    return this.map { it.toSize() }
}
