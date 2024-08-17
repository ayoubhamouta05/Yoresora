package com.youppix.ecommercecourse.di

import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.hilt.ScreenModelKey
import com.youppix.ecommercecourse.presentation.home_app.details.DetailsViewModel
import com.youppix.ecommercecourse.presentation.home_app.customSize.CustomSizeViewModel
import com.youppix.ecommercecourse.presentation.home_app.favorites.FavoritesViewModel
import com.youppix.ecommercecourse.presentation.home_app.home.HomeViewModel
import com.youppix.ecommercecourse.presentation.home_app.profile.ProfileScreenViewModel
import com.youppix.ecommercecourse.presentation.home_app.personalDetails.PersonalDetailsViewModel
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

    @Binds
    @IntoMap
    @ScreenModelKey(DetailsViewModel::class)
    abstract fun bindHiltDetailsViewModel(detailsViewModel: DetailsViewModel): ScreenModel

    @Binds
    @IntoMap
    @ScreenModelKey(FavoritesViewModel::class)
    abstract fun bindHiltFavoritesViewModel(favoritesViewModel: FavoritesViewModel): ScreenModel


    @Binds
    @IntoMap
    @ScreenModelKey(CustomSizeViewModel::class)
    abstract fun bindHiltCustomSizeViewModel(customSizeViewModel: CustomSizeViewModel): ScreenModel


    @Binds
    @IntoMap
    @ScreenModelKey(ProfileScreenViewModel::class)
    abstract fun bindHiltProfileScreenViewModel(profileScreenViewModel: ProfileScreenViewModel): ScreenModel

    @Binds
    @IntoMap
    @ScreenModelKey(PersonalDetailsViewModel::class)
    abstract fun bindHiltPersonalDetailsViewModel(personalDetailsViewModel: PersonalDetailsViewModel): ScreenModel

}