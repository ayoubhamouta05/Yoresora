package com.youppix.ecommercecourse.presentation.home_app.home

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.youppix.ecommercecourse.common.Resource
import com.youppix.ecommercecourse.domain.model.categories.Category
import com.youppix.ecommercecourse.domain.model.categories.toCategories
import com.youppix.ecommercecourse.domain.model.items.toItems
import com.youppix.ecommercecourse.domain.useCases.home.HomeUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val homeUseCases: HomeUseCases
) : ViewModel() {

    private var _homeState = mutableStateOf(HomeState(isLoading = true))
    val homeState: State<HomeState> = _homeState

    init {
        viewModelScope.launch {
            getHomeData()
            getAllItems()
        }
    }

    fun updateSearchQuery(value: String) {
        _homeState.value = homeState.value.copy(searchQuery = value)
    }

    fun updateCategorySelected(id : Int) {
        _homeState.value = homeState.value.copy(
            categorySelected = id
        )

        viewModelScope.launch {
            if (id > 1)
                getItemsByCategory(id)
            else
                getAllItems()
        }

    }


    suspend fun getHomeData() {
        homeUseCases.getHomeData().onEach { result ->
            when (result) {
                is Resource.Loading -> {
                    _homeState.value = homeState.value.copy(
                        isLoading = true
                    )
                }

                is Resource.Error -> {
                    _homeState.value = homeState.value.copy(
                        isLoading = false ,
                        getHomeDataError = result.message
                    )
                }

                is Resource.Successful -> {

                    _homeState.value = homeState.value.copy(
                        isLoading = false,
                        categories = result.data?.categories?.toCategories() ?: emptyList(),
                        flashSaleItems = result.data?.flashSaleItems?.toItems() ?: emptyList(),
                        newArrivals = result.data?.newArrivals?.toItems() ?: emptyList(),
                        getHomeDataError = null
                    )
                }
            }
            Log.d("HomeViewModel", "getHomeData: ${result.data}")

        }.launchIn(viewModelScope)
    }

    suspend fun getAllItems() {
        homeUseCases.getAllItems().onEach { result ->
            when (result) {
                is Resource.Loading -> {
                    _homeState.value = homeState.value.copy(
                        isLoading = true
                    )
                }

                is Resource.Error -> {
                    _homeState.value = homeState.value.copy(
                        isLoading = false,
                        getItemsError = result.data?.message ?: result.message
                        ?: "An Unexpected Error Occurred"
                    )
                }

                is Resource.Successful -> {
                    _homeState.value = homeState.value.copy(
                        isLoading = false,
                        items = result.data?.data?.toItems() ?: emptyList(),
                        getItemsError = null
                    )
                }
            }
            Log.d("HomeViewModel", "getAllItems: ${result.data}")
        }.launchIn(viewModelScope)
    }


    suspend fun getItemsByCategory(category: Int) {
        homeUseCases.getItemsByCategory(category).onEach { result ->
            when (result) {
                is Resource.Loading -> {
                    _homeState.value = homeState.value.copy(
                        isLoading = true
                    )
                }

                is Resource.Error -> {
                    _homeState.value = homeState.value.copy(
                        isLoading = false,
                        getItemsError = result.data?.message ?: result.message
                        ?: "An Unexpected Error Occurred"
                    )
                }

                is Resource.Successful -> {
                    _homeState.value = homeState.value.copy(
                        isLoading = false,
                        items = result.data?.data?.toItems() ?: emptyList(),
                        getItemsError = null
                    )
                }
            }
            Log.d("HomeViewModel", "getItemsByCategory: ${result.data}")
        }.launchIn(viewModelScope)
    }

}