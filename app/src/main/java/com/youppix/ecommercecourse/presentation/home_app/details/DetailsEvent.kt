package com.youppix.ecommercecourse.presentation.home_app.details

sealed class DetailsEvent {

    data class GetItemDetails(val itemId: Int , val userId : Int) : DetailsEvent()

    data class UpdateColorSelected(val color: Int) : DetailsEvent()

    data class CheckSizeExistence(val userId: Int) : DetailsEvent()

    data class UpdateSizeSelected(val size: Int) : DetailsEvent()

    data class UpdateFavoriteState(val userId : Int , val itemId : Int) : DetailsEvent()

    data object HideDialog : DetailsEvent()


}