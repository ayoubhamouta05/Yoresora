package com.youppix.ecommercecourse.presentation.home_app.favorites

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import com.youppix.ecommercecourse.common.Resource
import com.youppix.ecommercecourse.domain.model.categories.toCategories
import com.youppix.ecommercecourse.domain.model.items.toItems
import com.youppix.ecommercecourse.domain.useCases.favorites.FavoritesUseCases
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

class FavoritesViewModel @Inject constructor(
    private val favoritesUseCases: FavoritesUseCases
) : ScreenModel {

    private var _state =
        mutableStateOf(FavoritesState(categoriesLoading = true, itemsLoading = true))
    val state: State<FavoritesState> = _state

    init {
        onEvent(FavoriteEvent.GetAllCategories)
    }

    fun onEvent(event: FavoriteEvent) {
        when (event) {
            is FavoriteEvent.UpdateCategorySelected -> {
                _state.value = _state.value.copy(
                    categorySelected = if (event.category > 1) event.category else 0
                )
                screenModelScope.launch {
                    getAllFavorites(
                        categoryId = state.value.categorySelected,
                        userId = event.userId
                    )
                }
            }

            is FavoriteEvent.GetAllFavorites -> {
                screenModelScope.launch {
                    getAllFavorites(
                        categoryId = event.categoryId,
                        userId = event.userId
                    )
                }
            }

            is FavoriteEvent.GetAllCategories -> {
                screenModelScope.launch { getAllCategories() }
            }

        }
    }


    fun setUserId(id: Int) {
        _state.value = state.value.copy(
            userId = id
        )
    }

    suspend fun getAllCategories() {
        favoritesUseCases.getAllCategories().onEach { result ->
            when (result) {
                is Resource.Loading -> {
                    _state.value = state.value.copy(
                        categoriesLoading = true
                    )
                }

                is Resource.Error -> {
                    _state.value = state.value.copy(
                        categoriesLoading = false,
                        categoriesError = result.message ?: "Unknown error"
                    )
                }

                is Resource.Successful -> {
                    _state.value = state.value.copy(
                        categoriesLoading = false,
                        categories = result.data?.toCategories() ?: emptyList(),
                        categoriesError = null
                    )
                }
            }
        }.launchIn(screenModelScope)
    }


    private suspend fun getAllFavorites(userId: Int, categoryId: Int) {
        favoritesUseCases.getAllFavorites(userId = userId, categoryId = categoryId)
            .onEach { result ->
                when (result) {
                    is Resource.Loading -> {
                        _state.value = state.value.copy(
                            itemsLoading = true
                        )
                    }

                    is Resource.Error -> {
                        _state.value = state.value.copy(
                            itemsLoading = false,
                            itemsError = result.message ?: "Unknown Error"
                        )
                    }

                    is Resource.Successful -> {
                        _state.value = state.value.copy(
                            itemsLoading = false,
                            items = result.data?.data?.toItems() ?: emptyList(),
                            itemsError = null
                        )
                    }
                }
            }.launchIn(screenModelScope)
    }


}