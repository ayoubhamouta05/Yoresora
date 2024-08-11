package com.youppix.ecommercecourse.presentation.home_app.details

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import com.youppix.ecommercecourse.common.Resource
import com.youppix.ecommercecourse.domain.repository.details.DetailsRepository
import io.ktor.util.Identity.decode
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

class DetailsViewModel @Inject constructor(
    private val detailsRepository: DetailsRepository
) : ScreenModel {

    private val _state = mutableStateOf(DetailsState(isLoading = true))
    val state: State<DetailsState> = _state

    fun onEvent(event: DetailsEvent) {
        when (event) {
            is DetailsEvent.GetItemDetails -> {
                screenModelScope.launch {
                    getItemDetails(event.itemId , event.categoryId , event.userId)
                }
            }

            is DetailsEvent.UpdateSizeSelected -> {
                updateSizeSelected(event.size)
            }

            is DetailsEvent.UpdateColorSelected -> {
                updateColorSelected(event.color)
            }

            is DetailsEvent.UpdateFavoriteState -> {
                updateFavoriteState()
                screenModelScope.launch {
                    addOrDeleteFromFavorite(event.userId , event.itemId)
                }

            }
        }
    }

    fun setUserId(userId: Int){
        _state.value = state.value.copy(
            userId = userId
        )
    }

    private fun updateSizeSelected(size: Int) {
        _state.value = state.value.copy(
            selectedSize = size
        )
    }

    private fun updateColorSelected(color: Int) {
        _state.value = state.value.copy(
            selectedColors = color
        )
    }

    private fun updateFavoriteState(){
        _state.value = state.value.copy(
            details = state.value.details.copy(is_favorite = !state.value.details.is_favorite)
        )
    }

    private suspend fun getItemDetails(itemId: Int, categoryId: Int , userId: Int) {
        detailsRepository.getItemsDetails(itemId, categoryId , userId).onEach { result ->
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
                    runBlocking {
                        _state.value = state.value.copy(
                            isLoading = false,
                            details = result.data?.data ?: state.value.details,
                            error = null
                        )
                    }
                    _state.value = state.value.copy(
                        selectedSize = 0,
                        selectedColors = if (state.value.details.colors_id.isEmpty()) 0 else
                            state.value.details.colors_id[0],
                    )

                    Log.d("DetailsViewModel", "getItemDetails: ${state.value.details.sizes_id}")
                }
            }

        }.launchIn(screenModelScope)
    }

    private suspend fun addOrDeleteFromFavorite(userId: Int, itemId: Int) {
        detailsRepository.addOrDeleteFromFavorite(userId, itemId).onEach { result ->
            when (result) {
                is Resource.Loading -> {
                }

                is Resource.Error -> {
                    _state.value = state.value.copy(
                        details = state.value.details.copy(is_favorite =!state.value.details.is_favorite ), // to return it to last value
                        error = result.message ?: "An Unexpected Error Occurred"
                    )
                }

                is Resource.Successful -> {
                    _state.value = state.value.copy(
                        details = state.value.details.copy(is_favorite = state.value.details.is_favorite)
                    )
                }
            }
        }.launchIn(screenModelScope)
    }

}