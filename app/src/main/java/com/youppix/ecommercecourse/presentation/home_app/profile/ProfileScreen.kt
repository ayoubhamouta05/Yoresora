package com.youppix.ecommercecourse.presentation.home_app.profile

import android.content.Intent
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.Message
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.outlined.LocationOn
import androidx.compose.material.icons.outlined.Message
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.hilt.getNavigatorScreenModel
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.youppix.ecommercecourse.R
import com.youppix.ecommercecourse.common.Dimens.BottomBarHeight
import com.youppix.ecommercecourse.common.Dimens.ExtraSmallPadding2
import com.youppix.ecommercecourse.common.Dimens.MediumPadding
import com.youppix.ecommercecourse.common.Dimens.SmallPadding
import com.youppix.ecommercecourse.presentation.components.CustomDialog
import com.youppix.ecommercecourse.presentation.components.CustomTopAppBar
import com.youppix.ecommercecourse.presentation.home_app.address.AddressScreen
import com.youppix.ecommercecourse.presentation.home_app.home.HomeScreen
import com.youppix.ecommercecourse.presentation.home_app.personalDetails.PersonalDetailsScreen
import com.youppix.ecommercecourse.presentation.home_app.profile.components.ChangeLangSection
import com.youppix.ecommercecourse.presentation.home_app.profile.components.ImageSection
import com.youppix.ecommercecourse.presentation.home_app.profile.components.ProfileItem
import com.youppix.ecommercecourse.presentation.starting_app.StartActivity
import java.util.Locale


class ProfileScreen : Screen {
    @Composable
    override fun Content() {

        val navigator = LocalNavigator.currentOrThrow
        val viewModel: ProfileScreenViewModel = navigator.getNavigatorScreenModel()
        val state by viewModel.state
        val context = LocalContext.current
        val isArabic = remember {
            Locale.getDefault().language == "ar"
        }
        var dropLanguageMenu by remember {
            mutableStateOf(false)
        }

        LaunchedEffect(Unit) {
            viewModel.onEvent(ProfileEvent.GetUserData)
        }

        val singlePhotoPickerLauncher =
            rememberLauncherForActivityResult(contract = ActivityResultContracts.PickVisualMedia()) { uri ->
                uri?.let {
                    val file = viewModel.uriToFile(uri, context.contentResolver, context)
                    viewModel.onEvent(ProfileEvent.UploadImage(state.user.userId, file))
                }
            }

        Scaffold(
            modifier = Modifier.fillMaxSize(),
            topBar = {
                CustomTopAppBar(
                    title = stringResource(id = R.string.profile), isArabic = isArabic
                ) {
                    if (navigator.canPop) {
                        navigator.pop()
                    } else {
                        navigator.replace(HomeScreen())
                    }
                }
            },
        ) { innerPadding ->
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .padding(horizontal = MediumPadding)
                    .animateContentSize()
            ) {
                item() {
                    ImageSection(
                        selectedImageUri = state.user.userImage,
                        userName = state.user.userName
                    ) {
                        singlePhotoPickerLauncher.launch(
                            PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly)
                        )
                    }
                }

                item {
                    Column(
                        Modifier.fillMaxWidth(), verticalArrangement = Arrangement.Center
                    ) {
                        ProfileItem(
                            modifier = Modifier.padding(vertical = ExtraSmallPadding2),
                            isArabic = isArabic,
                            painter = painterResource(id = R.drawable.ic_person),
                            title = stringResource(id = R.string.personalDetails)
                        ) {
                            navigator.push(PersonalDetailsScreen(state.user))
                        }
                        Spacer(
                            modifier = Modifier
                                .height(0.5.dp)
                                .fillMaxWidth()
                                .padding(horizontal = SmallPadding)
                                .background(color = MaterialTheme.colorScheme.primary.copy(alpha = 0.3f))
                        )
                    }
                }
                item {
                    Column(
                        Modifier
                            .fillMaxWidth(), verticalArrangement = Arrangement.Center
                    ) {
                        ProfileItem(
                            modifier = Modifier.padding(vertical = ExtraSmallPadding2),
                            checked = state.isNotificationEnable,
                            imageVector = Icons.Outlined.Notifications,
                            title = stringResource(id = R.string.notifications)
                        ) {
                            viewModel.onEvent(ProfileEvent.ToggleNotification)
                        }
                        Spacer(
                            modifier = Modifier
                                .height(0.5.dp)
                                .fillMaxWidth()
                                .padding(horizontal = SmallPadding)
                                .background(color = MaterialTheme.colorScheme.primary.copy(alpha = 0.3f))
                        )
                    }
                }

                item {
                    Column(
                        Modifier.fillMaxWidth(), verticalArrangement = Arrangement.Center
                    ) {
                        ProfileItem(
                            modifier = Modifier.padding(vertical = ExtraSmallPadding2),
                            isArabic = isArabic,
                            imageVector = Icons.Outlined.LocationOn,
                            title = stringResource(id = R.string.address)
                        ) {
                            navigator.push(AddressScreen(userId = state.user.userId))
                        }
                        Spacer(
                            modifier = Modifier
                                .height(0.5.dp)
                                .fillMaxWidth()
                                .padding(horizontal = SmallPadding)
                                .background(color = MaterialTheme.colorScheme.primary.copy(alpha = 0.3f))
                        )
                    }
                }

                item {
                    Column(
                        Modifier.fillMaxWidth(), verticalArrangement = Arrangement.Center
                    ) {
                        ProfileItem(
                            modifier = Modifier.padding(vertical = ExtraSmallPadding2),
                            isArabic = isArabic,
                            painter = painterResource(id = R.drawable.ic_security),
                            title = stringResource(id = R.string.privacyPolicy)
                        ) {}
                        Spacer(
                            modifier = Modifier
                                .height(0.5.dp)
                                .fillMaxWidth()
                                .padding(horizontal = SmallPadding)
                                .background(color = MaterialTheme.colorScheme.primary.copy(alpha = 0.3f))
                        )
                    }
                }

                item {
                    ChangeLangSection(
                        isArabic = isArabic,
                        dropLanguageMenu = dropLanguageMenu,
                        onDismissRequest = { dropLanguageMenu = false },
                        onCLick = { dropLanguageMenu = !dropLanguageMenu },
                        event = viewModel::onEvent
                    )
                }

                item {
                    Column(
                        Modifier.fillMaxWidth(), verticalArrangement = Arrangement.Center
                    ) {
                        ProfileItem(
                            modifier = Modifier.padding(vertical = ExtraSmallPadding2),
                            isArabic = isArabic,
                            imageVector = Icons.AutoMirrored.Outlined.Message,
                            title = stringResource(id = R.string.contactUs)
                        ) {}
                        Spacer(
                            modifier = Modifier
                                .height(0.5.dp)
                                .fillMaxWidth()
                                .padding(horizontal = SmallPadding)
                                .background(color = MaterialTheme.colorScheme.primary.copy(alpha = 0.3f))
                        )
                    }
                }

                item {
                    ProfileItem(
                        modifier = Modifier
                            .padding(vertical = ExtraSmallPadding2)
                            .padding(bottom = BottomBarHeight.plus(MediumPadding * 2)),
                        isArabic = isArabic,
                        painter = painterResource(id = R.drawable.ic_logout),
                        title = stringResource(id = R.string.logout)
                    ) {
                        viewModel.onEvent(ProfileEvent.ShowDialog)
                    }
                }

            }

            CustomDialog(title = stringResource(id = R.string.logout),
                message = stringResource(id = R.string.logoutMessage),
                showDialog = state.showDialog,
                onConfirmRequest = {
                    viewModel.onEvent(ProfileEvent.Logout)
                    context.startActivity(Intent(context, StartActivity::class.java))
                },
                onDismissRequest = {
                    viewModel.onEvent(ProfileEvent.HideDialog)
                })

        }

    }
}