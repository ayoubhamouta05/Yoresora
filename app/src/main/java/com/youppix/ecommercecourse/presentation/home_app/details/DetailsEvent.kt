package com.youppix.ecommercecourse.presentation.home_app.details

sealed class DetailsEvent {

    data class GetItemDetails(val itemId: Int,val categoryId: Int) : DetailsEvent()

    data class UpdateColorSelected(val color: Int) : DetailsEvent()

    data class UpdateSizeSelected(val size: Int) : DetailsEvent()


}