package com.youppix.ecommercecourse.presentation.home_app.details

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import com.youppix.ecommercecourse.common.Resource
import com.youppix.ecommercecourse.domain.model.details.InitialColorAndSizeData
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

            is DetailsEvent.UpdateSizeSelected -> {
                updateSizeSelected(event.size)
            }

            is DetailsEvent.UpdateColorSelected -> {
                updateColorSelected(event.color)
            }

            is DetailsEvent.CheckSizeExistence -> {
                screenModelScope.launch {
                    checkSizeExistence(event.userId)
                }
            }

            is DetailsEvent.UpdateFavoriteState -> {
                updateFavoriteState()
                screenModelScope.launch {
                    addOrDeleteFromFavorite(event.userId, event.itemId)
                }

            }

            is DetailsEvent.HideDialog -> {
                _state.value = state.value.copy(
                    sizeAlreadyExistDialog = false,
                    goToCustomSizeScreen = false
                )
            }

            is DetailsEvent.AddOrDeleteCartItem -> {
                screenModelScope.launch {
                    addOrDeleteCartItem(
                        itemId = event.itemId,
                        userId = event.userId,
                        itemSize = event.itemSize,
                        itemColor = event.itemColor
                    )
                }
            }

            is DetailsEvent.ToggleAddToCart -> {
                _state.value = state.value.copy(
                    addToCartState = event.addToCart
                )
            }

            is DetailsEvent.SetInitialColorAndSize -> {
                setInitialColorAndSize(event.initialColor, event.initialSize, state.value)
            }
        }
    }

    private fun updateInitialData(itemSize: Int, itemColor: Int) {
        state.value.details.initialData?.let {
            var itemFound = false
            for (initialData in it) {
                if (initialData.itemSize == itemSize &&
                    initialData.itemColor == itemColor
                ) {
                    it.remove(initialData)
                    itemFound = true
                    break
                }
            }
            if (!itemFound) {
                it.add(
                    InitialColorAndSizeData(
                        itemSize = itemSize,
                        itemColor = itemColor
                    )
                )
            }
        }

    }

    fun setUserId(userId: Int) {
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
            selectedColor = color
        )
    }

    private fun updateFavoriteState() {
        _state.value = state.value.copy(
            details = state.value.details.copy(is_favorite = !state.value.details.is_favorite)
        )
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

    private suspend fun checkSizeExistence(userId: Int) {
        detailsUseCases.checkSizeExistence(userId).onEach { result ->
            when (result) {
                is Resource.Loading -> {
                    _state.value = state.value.copy(
                        checkSizeLoading = true
                    )
                }

                is Resource.Error -> {
                    _state.value = state.value.copy(
                        checkSizeLoading = false,
                        sizeAlreadyExistDialog = false,
                        goToCustomSizeScreen = true
                    )
                }

                is Resource.Successful -> {
                    _state.value = state.value.copy(
                        checkSizeLoading = false,
                        sizeAlreadyExistDialog = true,
                        goToCustomSizeScreen = false
                    )
                }
            }
        }.launchIn(screenModelScope)
    }

    private suspend fun addOrDeleteFromFavorite(userId: Int, itemId: Int) {
        detailsUseCases.addOrDeleteFromFavorite(userId, itemId).onEach { result ->
            when (result) {
                is Resource.Loading -> {
                }

                is Resource.Error -> {
                    _state.value = state.value.copy(
                        details = state.value.details.copy(is_favorite = !state.value.details.is_favorite), // to return it to last value
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

    private suspend fun addOrDeleteCartItem(
        itemId: Int,
        userId: Int,
        itemSize: Int,
        itemColor: Int,
    ) {
        detailsUseCases.addOrDeleteCartItem(itemId, userId, itemSize, itemColor).onEach { result ->
            when (result) {
                is Resource.Loading -> {
                    _state.value = state.value.copy(
                        isLoading = true
                    )
                }

                is Resource.Error -> {
                    _state.value = state.value.copy(
                        isLoading = false,
                        addToCartState = state.value.addToCartState
                    )
                }

                is Resource.Successful -> {
                    _state.value = state.value.copy(
                        isLoading = false,
                        addToCartState = !state.value.addToCartState,
                    )
                    updateInitialData(itemSize = itemSize, itemColor = itemColor)
                }
            }
        }.launchIn(screenModelScope)
    }

    private fun setInitialColorAndSize(
        initialColor: Int?,
        initialSize: Int?,
        state: DetailsState,
    ) {
        var size = 0
        for (i in 0 until state.details.sizes.size) {
            if (state.details.sizes[i].sizes_id == initialSize) {
                size = i
                break
            }
        }

        var color = 0
        for (i in 0 until state.details.colors.size) {
            if (state.details.colors[i].colors_id == initialColor) {
                color = i
                break
            }
        }

        if (initialColor != null && initialSize != null) {
            onEvent(DetailsEvent.UpdateColorSelected(color))
            onEvent(DetailsEvent.UpdateSizeSelected(size))
        } else {
            onEvent(DetailsEvent.UpdateColorSelected(0))
            onEvent(DetailsEvent.UpdateSizeSelected(0))
        }
    }
}