package com.youppix.ecommercecourse.presentation.home_app.details

sealed class DetailsEvent {

    data class GetItemDetails(val itemId: Int, val userId: Int) : DetailsEvent()

    data class UpdateColorSelected(val color: Int) : DetailsEvent()

    data class CheckSizeExistence(val userId: Int) : DetailsEvent()

    data class UpdateSizeSelected(val size: Int) : DetailsEvent()

    data class UpdateFavoriteState(val userId: Int, val itemId: Int) : DetailsEvent()

    data object HideDialog : DetailsEvent()

    data class AddOrDeleteCartItem(
        val itemId: Int,
        val userId: Int,
        val itemSize: Int,
        val itemColor: Int,
    ) : DetailsEvent()

    data class ToggleAddToCart(val addToCart: Boolean) : DetailsEvent()
    data class SetInitialColorAndSize(val initialColor: Int?,val  initialSize: Int?,val  state: DetailsState) : DetailsEvent()

}