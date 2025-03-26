package com.youppix.ecommercecourse.di

import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.hilt.ScreenModelKey
import com.youppix.ecommercecourse.presentation.admin_home_app.items.ItemsScreenViewModel
import com.youppix.ecommercecourse.presentation.admin_home_app.users.UsersViewModel
import com.youppix.ecommercecourse.presentation.user_home_app.address.AddressViewModel
import com.youppix.ecommercecourse.presentation.user_home_app.cart.CartViewModel
import com.youppix.ecommercecourse.presentation.user_home_app.checkout.CheckoutViewModel
import com.youppix.ecommercecourse.presentation.user_home_app.details.DetailsViewModel
import com.youppix.ecommercecourse.presentation.user_home_app.customSize.CustomSizeViewModel
import com.youppix.ecommercecourse.presentation.user_home_app.favorites.FavoritesViewModel
import com.youppix.ecommercecourse.presentation.user_home_app.home.HomeViewModel
import com.youppix.ecommercecourse.presentation.user_home_app.notification.NotificationsViewModel
import com.youppix.ecommercecourse.presentation.user_home_app.orders.OrdersViewModel
import com.youppix.ecommercecourse.presentation.user_home_app.ordersDetails.OrderDetailsViewModel
import com.youppix.ecommercecourse.presentation.user_home_app.payment.PaymentViewModel
import com.youppix.ecommercecourse.presentation.user_home_app.profile.ProfileScreenViewModel
import com.youppix.ecommercecourse.presentation.user_home_app.personalDetails.PersonalDetailsViewModel
import com.youppix.ecommercecourse.presentation.user_home_app.search.SearchViewModel
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

    @Binds
    @IntoMap
    @ScreenModelKey(CartViewModel::class)
    abstract fun bindHiltCartViewModel(cartViewModel: CartViewModel): ScreenModel

    @Binds
    @IntoMap
    @ScreenModelKey(CheckoutViewModel::class)
    abstract fun bindHiltCheckoutViewModel(checkoutViewModel: CheckoutViewModel): ScreenModel

    @Binds
    @IntoMap
    @ScreenModelKey(AddressViewModel::class)
    abstract fun bindHiltAddressViewModel(addressViewModel: AddressViewModel): ScreenModel

    @Binds
    @IntoMap
    @ScreenModelKey(PaymentViewModel::class)
    abstract fun bindHiltPaymentViewModel(paymentViewModel: PaymentViewModel): ScreenModel

    @Binds
    @IntoMap
    @ScreenModelKey(OrdersViewModel::class)
    abstract fun bindHiltOrdersViewModel(ordersViewModel: OrdersViewModel): ScreenModel

    @Binds
    @IntoMap
    @ScreenModelKey(OrderDetailsViewModel::class)
    abstract fun bindHiltOrderDetailsViewModel(orderDetailsViewModel: OrderDetailsViewModel): ScreenModel

    @Binds
    @IntoMap
    @ScreenModelKey(NotificationsViewModel::class)
    abstract fun bindHiltNotificationsViewModel(notificationsViewModel: NotificationsViewModel): ScreenModel

    @Binds
    @IntoMap
    @ScreenModelKey(ItemsScreenViewModel::class)
    abstract fun bindHiltItemsScreenViewModel(itemsScreenViewModel: ItemsScreenViewModel): ScreenModel

    @Binds
    @IntoMap
    @ScreenModelKey(com.youppix.ecommercecourse.presentation.admin_home_app.details.DetailsViewModel::class)
    abstract fun bindHiltDetailsViewModelForAdmin(detailsViewModel: com.youppix.ecommercecourse.presentation.admin_home_app.details.DetailsViewModel): ScreenModel

    @Binds
    @IntoMap
    @ScreenModelKey(com.youppix.ecommercecourse.presentation.admin_home_app.home.HomeViewModel::class)
    abstract fun bindHiltHomeViewModelForAdmin(homeViewModel: com.youppix.ecommercecourse.presentation.admin_home_app.home.HomeViewModel): ScreenModel

    @Binds
    @IntoMap
    @ScreenModelKey(UsersViewModel::class)
    abstract fun bindHiltUsersViewModel(usersViewModel: UsersViewModel): ScreenModel

}