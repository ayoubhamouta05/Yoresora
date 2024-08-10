package com.youppix.ecommercecourse.presentation.home_app.details.customSize

sealed class CustomSizeEvent {

    data class UpdateShoulderWidth(val value : String ) : CustomSizeEvent()
    data class UpdateChestCircumference(val value : String ) : CustomSizeEvent()
    data class UpdateChestHeight(val value : String ) : CustomSizeEvent()
    data class UpdateWaistLine(val value : String ) : CustomSizeEvent()
    data class UpdateButtocksCircumference(val value : String ) : CustomSizeEvent()
    data class UpdateButtocksHeight(val value : String ) : CustomSizeEvent()
    data class UpdateArmCircumference(val value : String ) : CustomSizeEvent()
    data class UpdateWristCircumference(val value : String ) : CustomSizeEvent()
    data class UpdateDesiredArmLength(val value : String ) : CustomSizeEvent()
    data class UpdateTotalLength(val value : Float ) : CustomSizeEvent()
    data class UpdateUserId(val value : Int ) : CustomSizeEvent()
    data class UpdateName (val value : String ) : CustomSizeEvent()
    data class OnConfirm(val value : CustomSizeState) : CustomSizeEvent()
    data object OnReset : CustomSizeEvent()
    data object HideDialog : CustomSizeEvent()

}