package com.youppix.ecommercecourse.presentation.home_app.search

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import com.youppix.ecommercecourse.common.Resource
import com.youppix.ecommercecourse.domain.model.categories.toCategories
import com.youppix.ecommercecourse.domain.model.items.FilteringItems
import com.youppix.ecommercecourse.domain.model.items.toItems
import com.youppix.ecommercecourse.domain.useCases.search.SearchUseCases
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject


class SearchViewModel @Inject constructor(
    private val searchUseCases: SearchUseCases
) : ScreenModel {


    private var _state = mutableStateOf(
        SearchState(
            itemsLoading = true,
            categoriesLoading = true
        )
    )
    val state: State<SearchState> = _state

    init {
        screenModelScope.launch {
            getAllCategories()
            getItemsByFiltering(state.value.filteringItems)
        }
    }


    private fun updateSearchQuery(value: String) {
        _state.value = state.value.copy(searchQuery = value)
    }

    private fun updateFilteringItems(filteringItems: FilteringItems){
        _state.value = state.value.copy(filteringItems = filteringItems)
        screenModelScope.launch {
            getItemsByFiltering(state.value.filteringItems)
        }
    }

    fun onEvent(event: SearchEvent) {
        when (event) {
            is SearchEvent.UpdateSearchQuery -> {
                updateSearchQuery(event.value)
            }

            is SearchEvent.UpdateCategorySelected -> {
                updateCategorySelected(event.id)
            }

            is SearchEvent.UpdateFilteringItems ->{
                updateFilteringItems(event.filteringItems)
            }

            is SearchEvent.GetAllCategories -> {
                screenModelScope.launch {
                    getAllCategories()
                }
            }

            is SearchEvent.GetItemsByFiltering -> {
                screenModelScope.launch {
                    getItemsByFiltering(event.filteringItems)
                }
            }
        }
    }

    private fun updateCategorySelected(id: Int) {
        _state.value = state.value.copy(
            filteringItems = state.value.filteringItems.copy(itemsCat = id),
        )

        screenModelScope.launch {
            if (id > 1)
                getItemsByFiltering(
                    filteringItems = state.value.filteringItems.copy(
                        itemsCat = id
                    )
                )
            else
                getItemsByFiltering(
                    filteringItems = state.value.filteringItems.copy(
                        itemsCat = null
                    )
                )

        }

    }


    private suspend fun getAllCategories() {
        searchUseCases.getAllCategories().onEach { result ->
            when (result) {
                is Resource.Loading -> {
                    _state.value = state.value.copy(
                        categoriesLoading = true
                    )
                }

                is Resource.Error -> {
                    _state.value = state.value.copy(
                        categoriesLoading = false,
                        getCategoriesError = result.message ?: "An Unexpected Error Occurred"
                    )
                }

                is Resource.Successful -> {
                    _state.value = state.value.copy(
                        categoriesLoading = false,
                        getCategoriesError = null,
                        categories = result.data?.toCategories() ?: emptyList()
                    )
                }
            }
            Log.d("SearchViewModel", "categories result : ${result.data}")
        }.launchIn(screenModelScope)
    }


    private suspend fun getItemsByFiltering(filteringItems: FilteringItems) {
        searchUseCases.getItemsByFiltering(filteringItems).onEach { result ->
            when (result) {
                is Resource.Loading -> {
                    _state.value = state.value.copy(
                        itemsLoading = true
                    )
                }

                is Resource.Error -> {
                    _state.value = state.value.copy(
                        itemsLoading = false,
                        getItemsError = result.message ?: "An Unexpected Error Occurred"
                    )
                }

                is Resource.Successful -> {
                    _state.value = state.value.copy(
                        itemsLoading = false,
                        getItemsError = null,
                        items = result.data?.data?.toItems() ?: emptyList()
                    )
                }
            }
            Log.d("SearchViewModel", "items result : ${result.data}")
        }.launchIn(screenModelScope)
    }
}