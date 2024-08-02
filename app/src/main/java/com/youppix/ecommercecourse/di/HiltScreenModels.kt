package com.youppix.ecommercecourse.di

import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.hilt.ScreenModelKey
import com.youppix.ecommercecourse.presentation.home_app.home.HomeViewModel
import com.youppix.ecommercecourse.presentation.home_app.search.SearchViewModel
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.multibindings.IntoMap

@Module
@InstallIn(ActivityComponent::class)
abstract class HiltScreenModels {


    @Binds
    @IntoMap
    @ScreenModelKey(HomeViewModel::class)
    abstract fun bindHiltHomeViewModel(homeViewModel: HomeViewModel): ScreenModel


    @Binds
    @IntoMap
    @ScreenModelKey(SearchViewModel::class)
    abstract fun bindHiltSearchViewModel(searchViewModel: SearchViewModel): ScreenModel


}