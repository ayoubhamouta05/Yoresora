package com.youppix.ecommercecourse.presentation.home_app.details.customSize

data class CustomSizeState(
    val isLoading : Boolean = false,
    val userId : Int?=null,
    val customSizeName : String = "Custom Size",
    val shoulderWidth : String = "0.0",
    val chestCircumference : String = "0.0",
    val chestHeight : String = "0.0",
    val waistLine : String = "0.0",
    val buttocksCircumference : String = "0.0",
    val buttocksHeight : String = "0.0",
    val armCircumference : String = "0.0",
    val wristCircumference : String = "0.0",
    val desiredArmLength : String = "0.0",
    val totalLength : Float = 150f,
    val isSuccessful : Boolean = false,
    val showAlertDialog : Boolean = false,
    val showErrorDialog : Boolean = false
)
