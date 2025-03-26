package com.youppix.ecommercecourse.presentation.admin_home_app.details

import android.content.Context
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import com.youppix.ecommercecourse.R
import com.youppix.ecommercecourse.common.Resource
import com.youppix.ecommercecourse.domain.model.items.Item
import com.youppix.ecommercecourse.domain.useCases.details.DetailsUseCases
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

class DetailsViewModel @Inject constructor(
    private val detailsUseCases: DetailsUseCases,
) : ScreenModel {

    private val _state = mutableStateOf(DetailsState(isLoading = true))
    val state: State<DetailsState> = _state



    fun onEvent(event: DetailsEvent) {
        when (event) {
            is DetailsEvent.GetItemDetails -> {
                screenModelScope.launch {
                    getItemDetails(event.itemId, event.userId)
                }
            }

            is DetailsEvent.HideDialog -> {
                _state.value = state.value.copy(
                    isLoading = false,
                )
            }

            is DetailsEvent.ToggleActiveItem -> {
                _state.value = state.value.copy(
                    details = state.value.details.copy(
                        is_active = event.isActive
                    )
                )
            }

            is DetailsEvent.SaveChanges -> {
                screenModelScope.launch {
//                    detailsUseCases.saveChanges(event.item)
                }
            }

            is DetailsEvent.UpdateDescriptionArab -> {
                updateItemDescriptionArab(event.value)
            }

            is DetailsEvent.UpdateDescriptionEng -> {
                updateItemDescriptionEng(event.value)
            }

            is DetailsEvent.UpdateDiscount -> {
                updateItemDiscount(event.value)
            }

            is DetailsEvent.UpdateNameArab ->{
                updateItemNameArab(event.value)
            }

            is DetailsEvent.UpdateNameEng -> {
                updateItemNameEng(event.value)
            }

            is DetailsEvent.InitialItemData -> {
                initialItemData(event.item)
            }
        }
    }

    private fun initialItemData(item: Item) {
        _state.value = state.value.copy(
            item = item
        )
    }

    private fun updateItemNameEng(value: String) {
        _state.value = state.value.copy(
            item = state.value.item.copy(
                itemName = value
            )
        )
    }

    private fun updateItemNameArab(value: String) {
        _state.value = state.value.copy(
            item = state.value.item.copy(
                itemNameAr = value
            )
        )
    }


    fun setUserId(userId: Int) {
        _state.value = state.value.copy(
            userId = userId
        )
    }


    private fun updateItemDescriptionEng(value: String) {
        _state.value = state.value.copy(
            item = state.value.item.copy(
                itemDesc = value
            )

        )
    }

    private fun updateItemDescriptionArab(value: String) {
        _state.value = state.value.copy(
            item = state.value.item.copy(
                itemDescAr = value
            )
        )
    }

    private fun updateItemDiscount(value: String) {
        if (value.isEmpty()) {
            onEvent(DetailsEvent.UpdateDiscount("0"))
        }else{
            if(value.length <=2){
                if (checkIfNumber(value)) {
                    _state.value = state.value.copy(
                        item = state.value.item.copy(
                            itemDiscount = value.toInt()
                        )
                    )
                }
            }

        }

    }


    private suspend fun getItemDetails(itemId: Int, userId: Int) {
        detailsUseCases.getItemDetails(itemId, userId).onEach { result ->
            when (result) {
                is Resource.Loading -> {
                    _state.value = state.value.copy(
                        isLoading = true
                    )
                }

                is Resource.Error -> {
                    _state.value = state.value.copy(
                        isLoading = false,
                        error = result.message ?: "An Unexpected Error Occurred"
                    )
                }

                is Resource.Successful -> {
                    _state.value = state.value.copy(
                        isLoading = false,
                        details = result.data?.data ?: state.value.details,
                        error = null
                    )
                }
            }

        }.launchIn(screenModelScope)
    }

    private fun checkIfNumber(values: String): Boolean {

        try {
            if (values.count { it == '.' } > 1) {
                return false
            }
            values.toFloat()
            return true
        } catch (e: Exception) {
            return false
        }
    }


}