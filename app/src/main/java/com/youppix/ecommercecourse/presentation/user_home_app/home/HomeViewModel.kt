package com.youppix.ecommercecourse.presentation.user_home_app.home

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import com.youppix.ecommercecourse.common.Resource
import com.youppix.ecommercecourse.domain.model.categories.toCategories
import com.youppix.ecommercecourse.domain.model.items.toItems
import com.youppix.ecommercecourse.domain.useCases.home.HomeUseCases
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject


class HomeViewModel @Inject constructor(
    private val homeUseCases: HomeUseCases,
) : ScreenModel {

    private var _homeState = mutableStateOf(HomeState(isHomeLoading = true))
    val homeState: State<HomeState> = _homeState



    fun onEvent(event: HomeEvent) {
        when (event) {
            is HomeEvent.UpdateCategorySelected -> {
                updateCategorySelected(event.id)
            }

            is HomeEvent.GetHomeData -> {
                screenModelScope.launch {
                    getHomeData(userId = event.userId)
                }
            }


            is HomeEvent.GetItemsByCategory -> {
                screenModelScope.launch {
                    getItemsByCategory(event.category)
                }
            }

            is HomeEvent.SetUserId -> {
                _homeState.value = homeState.value.copy(
                    userId = event.userId
                )
            }

        }
    }


    private fun updateCategorySelected(id: Int) {
        _homeState.value = homeState.value.copy(
            categorySelected = id
        )
        screenModelScope.launch {
            if (homeState.value.categories.isNotEmpty())
                getItemsByCategory(homeState.value.categories[id].id)
        }

    }


    private suspend fun getHomeData(userId: Int) {
        homeUseCases.getHomeData(userId).onEach { result ->
            when (result) {
                is Resource.Loading -> {
                    _homeState.value = homeState.value.copy(
                        isHomeLoading = true ,
                        getHomeDataError = null
                    )
                }

                is Resource.Error -> {
                    _homeState.value = homeState.value.copy(
                        isHomeLoading = false,
                        getHomeDataError = result.message
                    )
                }

                is Resource.Successful -> {

                    _homeState.value = homeState.value.copy(
                        isHomeLoading = false,
                        categories = result.data?.categories?.toCategories() ?: emptyList(),
                        flashSaleItems = result.data?.flashSaleItems?.toItems() ?: emptyList(),
                        newArrivals = result.data?.newArrivals?.toItems() ?: emptyList(),
                        getHomeDataError = null ,
                        haveNotification = result.data?.haveNotification ?: false

                    )
                }
            }
            Log.d("HomeViewModel", "getHomeData: ${result.data}")

        }.launchIn(screenModelScope)
    }


    private suspend fun getItemsByCategory(category: Int) {
        homeUseCases.getItemsByCategory(category).onEach { result ->
            when (result) {
                is Resource.Loading -> {
                    _homeState.value = homeState.value.copy(
                        isItemsCategoriesLoading = true,
                        getItemsError = null,
                    )
                }

                is Resource.Error -> {
                    _homeState.value = homeState.value.copy(
                        isItemsCategoriesLoading = false,
                        getItemsError = result.data?.message ?: result.message
                        ?: "An Unexpected Error Occurred"
                    )
                }

                is Resource.Successful -> {
                    _homeState.value = homeState.value.copy(
                        isItemsCategoriesLoading = false,
                        items = result.data?.data?.toItems() ?: emptyList(),
                        getItemsError = null
                    )
                }
            }
            Log.d("HomeViewModel", "getItemsByCategory: ${result.data}")
        }.launchIn(screenModelScope)
    }

}