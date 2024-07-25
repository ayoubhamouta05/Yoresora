package com.youppix.ecommercecourse.presentation.home_app.components

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Message
import androidx.compose.material.icons.automirrored.outlined.Message
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Message
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.ShoppingBag
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.ShoppingBag
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.navigator.Navigator
import com.youppix.ecommercecourse.common.Dimens.ExtraSmallPadding
import com.youppix.ecommercecourse.common.Dimens.SmallPadding
import com.youppix.ecommercecourse.presentation.home_app.chat.ChatScreen
import com.youppix.ecommercecourse.presentation.home_app.favorites.FavoritesScreen
import com.youppix.ecommercecourse.presentation.home_app.home.HomeScreen
import com.youppix.ecommercecourse.presentation.home_app.profile.ProfileScreen


@Composable
fun CustomBottomBar(
    modifier: Modifier = Modifier,
    currentScreen: Int,
    onChangeNav: (Int) -> Unit
) {

    val interactionSource = remember { MutableInteractionSource() }

    LazyRow(
        modifier = modifier
            .fillMaxWidth()
            .height(70.dp)
            .clip(CircleShape)
            .background(MaterialTheme.colorScheme.onBackground),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        contentPadding = PaddingValues(SmallPadding)
    ) {

        item {
            Box(
                modifier = Modifier
                    .background(
                        color = if (currentScreen == 0) Color.LightGray else Color.Transparent,
                        shape = CircleShape
                    )
                    .padding(SmallPadding)
                    .clickable(
                        indication = null,
                        interactionSource = interactionSource
                    ) { onChangeNav(0) }) {
                Icon(
                    imageVector = if (currentScreen == 0) Icons.Filled.Home else Icons.Outlined.Home,
                    contentDescription = "home",
                    tint = if (currentScreen == 0) Color.Black else Color.Gray,
                    modifier = Modifier.size(25.dp)
                )
            }

        }

        item {
            Box(
                modifier = Modifier
                    .background(
                        color = if (currentScreen == 1) Color.LightGray else Color.Transparent,
                        shape = CircleShape
                    )
                    .padding(SmallPadding)
                    .clickable(
                        indication = null,
                        interactionSource = interactionSource
                    ) { onChangeNav(1) }) {
                Icon(
                    imageVector = if (currentScreen == 1) Icons.Filled.ShoppingBag else Icons.Outlined.ShoppingBag,
                    contentDescription = "Shop",
                    tint = if (currentScreen == 1) Color.Black else Color.Gray,
                    modifier = Modifier.size(25.dp)
                )
            }

        }

        item {
            Box(
                modifier = Modifier
                    .background(
                        color = if (currentScreen == 2) Color.LightGray else Color.Transparent,
                        shape = CircleShape
                    )
                    .padding(SmallPadding)
                    .clickable(
                        indication = null,
                        interactionSource = interactionSource
                    ) { onChangeNav(2) }) {
                Icon(
                    imageVector = if (currentScreen == 2) Icons.Filled.Favorite else Icons.Outlined.FavoriteBorder,
                    contentDescription = "Favorite",
                    tint = if (currentScreen == 2) Color.Black else Color.Gray,
                    modifier = Modifier.size(25.dp)
                )
            }
        }
        item {
            Box(
                modifier = Modifier
                    .background(
                        color = if (currentScreen == 3) Color.LightGray else Color.Transparent,
                        shape = CircleShape
                    )
                    .padding(SmallPadding)
                    .clickable(
                        indication = null,
                        interactionSource = interactionSource
                    ) { onChangeNav(3) }) {
                Icon(
                    imageVector = if (currentScreen == 3) Icons.AutoMirrored.Filled.Message else Icons.AutoMirrored.Outlined.Message,
                    contentDescription = "Chat",
                    tint = if (currentScreen == 3) Color.Black else Color.Gray,
                    modifier = Modifier.size(25.dp)
                )
            }
        }

        item {
            Box(
                modifier = Modifier
                    .background(
                        color = if (currentScreen == 4) Color.LightGray else Color.Transparent,
                        shape = CircleShape
                    )
                    .padding(SmallPadding)
                    .clickable(
                        indication = null,
                        interactionSource = interactionSource
                    ) {
                        onChangeNav(4)
                    }) {
                Icon(
                    imageVector = if (currentScreen == 4) Icons.Filled.Person else Icons.Outlined.Person,
                    contentDescription = "Profile",
                    tint = if (currentScreen == 4) Color.Black else Color.Gray,
                    modifier = Modifier.size(25.dp)
                )
            }
        }
    }


}