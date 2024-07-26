package com.youppix.ecommercecourse.presentation.home_app.home

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.youppix.ecommercecourse.common.Resource
import com.youppix.ecommercecourse.domain.model.categories.toCategories
import com.youppix.ecommercecourse.domain.model.items.toItems
import com.youppix.ecommercecourse.domain.useCases.home.HomeUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val homeUseCases: HomeUseCases
) : ViewModel() {

    private var _homeState = mutableStateOf(HomeState())
    val homeState: State<HomeState> = _homeState

    fun updateSearchQuery(value: String) {
        _homeState.value = homeState.value.copy(searchQuery = value)
    }

    fun updateCategorySelected(category: String) {
        _homeState.value = homeState.value.copy(categorySelected = category)
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
                        isLoading = false
                    )
                }

                is Resource.Successful -> {
                    _homeState.value = homeState.value.copy(
                        isLoading = false ,
                        categories = result.data?.categories?.toCategories()?: emptyList(),
                        items = result.data?.items?.toItems()?: emptyList()
                    )
                }
            }
            Log.d("HomeViewModel", "getHomeData: ${result.data}")

        }.launchIn(viewModelScope)
    }


}