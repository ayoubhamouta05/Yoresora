package com.youppix.ecommercecourse.presentation.home_app.profile

import android.content.ContentResolver
import android.content.Context
import android.net.Uri
import android.webkit.MimeTypeMap
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import com.google.firebase.messaging.FirebaseMessaging
import com.youppix.ecommercecourse.common.Constant.APP_ENTRY
import com.youppix.ecommercecourse.common.Constant.APP_LANG
import com.youppix.ecommercecourse.common.Resource
import com.youppix.ecommercecourse.domain.useCases.profile.ProfileUseCases
import com.youppix.ecommercecourse.presentation.components.isNotificationPermissionGranted
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import java.io.File
import java.io.FileOutputStream
import java.io.InputStream
import javax.inject.Inject

class ProfileScreenViewModel @Inject constructor(
    private val profileUseCases: ProfileUseCases
) : ScreenModel {

    private var _state = mutableStateOf(ProfileState())
    val state: State<ProfileState> = _state

    fun onEvent(event: ProfileEvent) {
        when (event) {
            is ProfileEvent.SetUserId -> {
                _state.value = state.value.copy(
                    user = state.value.user.copy(
                        userId = event.userId
                    )
                )
            }

            is ProfileEvent.Logout -> {
                logout()
                onEvent(ProfileEvent.HideDialog)
            }

            is ProfileEvent.ShowDialog -> {
                _state.value = state.value.copy(
                    showDialog = true
                )
            }

            is ProfileEvent.HideDialog -> {
                _state.value = state.value.copy(
                    showDialog = false
                )
            }

            is ProfileEvent.ToggleNotification -> {
                if(event.value){
                    profileUseCases.saveUserData("subscribeToTopics" , true.toString())
                    FirebaseMessaging.getInstance().subscribeToTopic("users")
                    FirebaseMessaging.getInstance().subscribeToTopic(state.value.user.userId.toString())
                }else{
                    profileUseCases.saveUserData("subscribeToTopics" , false.toString())
                    FirebaseMessaging.getInstance().unsubscribeFromTopic("users")
                    FirebaseMessaging.getInstance().unsubscribeFromTopic(state.value.user.userId.toString())
                }
                _state.value = state.value.copy(
                    isNotificationEnable = event.value
                )
            }

            is ProfileEvent.UploadImage -> {
                screenModelScope.launch {
                    uploadImage(event.userId, event.file)
                }
            }

            is ProfileEvent.GetUserData -> {
                getUserData()
                val isNotificationEnable = profileUseCases.getUserData("subscribeToTopics", false.toString()).toBoolean()
                        && isNotificationPermissionGranted(event.context)
                _state.value = state.value.copy(
                    isNotificationEnable = isNotificationEnable
                )
            }

            is ProfileEvent.SaveAppLanguage -> {
                screenModelScope.launch {
                    profileUseCases.saveAppLanguage(APP_LANG, event.lang)
                }
            }

        }
    }

    private fun logout() {
        profileUseCases.saveUserData(APP_ENTRY, "1")
        FirebaseMessaging.getInstance().unsubscribeFromTopic("users")
        FirebaseMessaging.getInstance().unsubscribeFromTopic(state.value.user.userId.toString())
    }

    private suspend fun uploadImage(userId: Int, file: File) {
        profileUseCases.uploadImage(userId, file).onEach { result ->
            when (result) {
                is Resource.Loading -> {
                    _state.value = state.value.copy(
                        isLoading = true
                    )
                }

                is Resource.Error -> {
                    _state.value = state.value.copy(
                        isLoading = false,
                        error = result.message ?: result.data?.message ?: "An Unexpected Error Occurred"
                    )
                    file.delete()
                }

                is Resource.Successful -> {
                    result.data?.message?.let {
                        updateUserImage(it)
                        profileUseCases.saveUserData("userImage" , it)
                    }

                    _state.value = state.value.copy(
                        isLoading = false ,
                        error = null
                    )
                    file.delete()
                }
            }
        }.launchIn(screenModelScope)
    }


    fun uriToFile(uri: Uri, contentResolver: ContentResolver, context: Context): File {


        val mimeType = contentResolver.getType(uri)
        val fileExtension = MimeTypeMap.getSingleton().getExtensionFromMimeType(mimeType)
        val tempFile = File.createTempFile("tempImage", ".$fileExtension", context.cacheDir)
        val inputStream: InputStream? = contentResolver.openInputStream(uri)
        val outputStream = FileOutputStream(tempFile)

        inputStream?.use { input ->
            outputStream.use { output ->
                input.copyTo(output)
            }
        }
        return tempFile
    }

    private fun getUserData() {
        val userId = profileUseCases.getUserData("userId", "")
        val userName = profileUseCases.getUserData("userName", "")
        val userEmail = profileUseCases.getUserData("userEmail", "")
        val userPhone = profileUseCases.getUserData("userPhone", "")
        val userImage = profileUseCases.getUserData("userImage", "")
        val userCustomerId = profileUseCases.getUserData("userCustomerId", "")

        _state.value = state.value.copy(
            user = state.value.user.copy(
                userId = if (userId.isNotEmpty()) userId.toInt() else state.value.user.userId,
                userName = userName.ifEmpty { state.value.user.userName },
                userEmail = userEmail.ifEmpty { state.value.user.userEmail },
                userPhone = userPhone.ifEmpty { state.value.user.userPhone },
                userImage = userImage.ifEmpty { state.value.user.userName },
                userCustomerId = userCustomerId.ifEmpty { state.value.user.userCustomerId }
            )
        )
    }

    private fun updateUserImage(userImage : String) {
        _state.value = state.value.copy(
            user = state.value.user.copy(
                userImage = userImage
            )
        )
    }

}