package com.youppix.ecommercecourse.presentation.home_app.details

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import com.youppix.ecommercecourse.common.Resource
import com.youppix.ecommercecourse.domain.repository.details.DetailsRepository
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

class DetailsViewModel @Inject constructor(
    private val detailsRepository: DetailsRepository
) : ScreenModel {

    private val _state = mutableStateOf(DetailsState(isLoading = true))
    val state: State<DetailsState> = _state


    fun updateSizeSelected(size: Int) {
        _state.value = state.value.copy(
            selectedSize = size.plus(1)
        )
    }
    fun updateColorSelected(color : Int){
        _state.value= state.value.copy(
            selectedColors = color
        )
    }

    suspend fun getDetails(itemId: Int, categoryId: Int) {
        detailsRepository.getItemsDetails(itemId, categoryId).onEach { result ->
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
                        selectedSize = if (state.value.details.sizes_id.isEmpty()) 0 else state.value.details.sizes_id[0],
                        selectedColors = if (state.value.details.colors_id.isEmpty()) 0 else
                            state.value.details.colors_id[0]

                    )
                }
            }

        }.launchIn(screenModelScope)
    }


}