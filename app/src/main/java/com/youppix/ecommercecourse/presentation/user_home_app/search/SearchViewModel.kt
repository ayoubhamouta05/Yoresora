package com.youppix.ecommercecourse.presentation.user_home_app.search

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
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
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

    private var _searchQuery = MutableStateFlow("")
    val searchQuery: StateFlow<String> = _searchQuery

    init {
        screenModelScope.launch {
            getAllCategories()
            getItemsByFiltering(state.value.filteringItems)
            getAllColors()
        }
    }


    @OptIn(FlowPreview::class)
    private fun updateSearchQuery(value: String) {
        _searchQuery.value = value

        screenModelScope.launch {
            searchQuery.debounce(500).distinctUntilChanged().collectLatest { query ->
                _state.value = state.value.copy(
                    filteringItems = state.value.filteringItems.copy(
                        itemsName = query
                    )
                )
                getItemsByFiltering(state.value.filteringItems)
            }
        }

    }

    private fun updateFilteringItems(filteringItems: FilteringItems, sendRequest: Boolean) {
        _state.value = state.value.copy(filteringItems = filteringItems)
        if (sendRequest) {
            screenModelScope.launch {
                getItemsByFiltering(state.value.filteringItems)
            }
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

            is SearchEvent.UpdateFilteringItems -> {
                updateFilteringItems(event.filteringItems, event.sendRequest)
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
            filteringItems = state.value.filteringItems.copy(itemsCat =id)
        )

        screenModelScope.launch {
            getItemsByFiltering(
                filteringItems = state.value.filteringItems.copy(itemsCat = state.value.categories[id].id)
            )
        }
    }


    private suspend fun getAllCategories() {
        searchUseCases.getAllCategories().onEach { result ->
            when (result) {
                is Resource.Loading -> {
                    _state.value = state.value.copy(
                        categoriesLoading = true,
                        getCategoriesError = null
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
                        itemsLoading = true ,
                        getItemsError = null
                    )
                }

                is Resource.Error -> {
                    _state.value = state.value.copy(
                        itemsLoading = false,
                        getItemsError = result.message ?: "An Unexpected Error Occurred"
                    )
                }

                is Resource.Successful -> {
                    val maxPrice = result.data?.maxPrice
                    if (maxPrice != null && maxPrice < state.value.filteringItems.finalPrice) {
                        _state.value = state.value.copy(
                            filteringItems = state.value.filteringItems.copy(
                                finalPrice = maxPrice
                            )
                        )
                    }
                    val minPrice = result.data?.minPrice
                    if (minPrice != null && minPrice > state.value.filteringItems.initialPrice) {
                        _state.value = state.value.copy(
                            filteringItems = state.value.filteringItems.copy(
                                initialPrice = minPrice
                            )
                        )
                    }
                    _state.value = state.value.copy(
                        filteringItems = state.value.filteringItems.copy(
                            maxPrice = result.data?.maxPrice ?: state.value.filteringItems.maxPrice,
                            minPrice = result.data?.minPrice ?: state.value.filteringItems.minPrice
                        ),
                        itemsLoading = false,
                        getItemsError = null,
                        items = result.data?.data?.toItems() ?: emptyList()
                    )
                }
            }
            Log.d("searchViewModel", "getItemsByFiltering : result : ${result.data}")
        }.launchIn(screenModelScope)
    }


    private suspend fun getAllColors() {
        searchUseCases.getAllColors().onEach { result ->
            when (result) {
                is Resource.Loading -> {}

                is Resource.Error -> {}

                is Resource.Successful -> {
                    _state.value = state.value.copy(
                        allColors = result.data ?: emptyList()
                    )
                }
            }
            Log.d("SearchViewModel", "items result : ${result.data}")
        }.launchIn(screenModelScope)

    }

}