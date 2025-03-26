package com.youppix.ecommercecourse.presentation.admin_home_app.details

import com.youppix.ecommercecourse.domain.model.items.Item

sealed class DetailsEvent {

    data class GetItemDetails(val itemId: Int, val userId: Int) : DetailsEvent()

    data object HideDialog : DetailsEvent()

    data class ToggleActiveItem (val isActive : Boolean) : DetailsEvent()

    data class SaveChanges(val item: Item) : DetailsEvent()

    data class UpdateDescriptionArab(val value : String) : DetailsEvent()

    data class UpdateDescriptionEng(val value : String) : DetailsEvent()

    data class UpdateNameArab(val value : String) : DetailsEvent()

    data class UpdateNameEng(val value : String) : DetailsEvent()

    data class UpdateDiscount(val value : String) : DetailsEvent()

    data class InitialItemData(val item : Item) : DetailsEvent()

}