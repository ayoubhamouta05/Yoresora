package com.youppix.ecommercecourse.presentation.home_app.personalDetails

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import cafe.adriel.voyager.core.model.ScreenModel
import com.youppix.ecommercecourse.domain.model.user.User
import javax.inject.Inject

class PersonalDetailsViewModel @Inject constructor(
): ScreenModel {

    private var _state = mutableStateOf(PersonalDetailsState())
    val state: State<PersonalDetailsState> = _state

    fun onEvent(event: PersonalDetailsEvent) {
        when (event) {
            is PersonalDetailsEvent.UpdateUserName -> {
                _state.value = _state.value.copy(
                    user = state.value.user.copy(
                        userName = event.name
                    )
                )
            }

            is PersonalDetailsEvent.UpdateEmail -> {

                _state.value = _state.value.copy(
                    user = state.value.user.copy(
                        userEmail = event.email
                    )
                )

            }

            is PersonalDetailsEvent.UpdatePhone -> {
                _state.value = _state.value.copy(
                    user = state.value.user.copy(
                        userPhone = event.phone
                    )
                )
            }

            is PersonalDetailsEvent.UpdatePassword -> {
                _state.value = _state.value.copy(
                    oldPassword = event.password
                )
            }

            is PersonalDetailsEvent.UpdateNewPassword -> {
                _state.value = _state.value.copy(
                    newPassword = event.newPassword
                )
            }

            is PersonalDetailsEvent.ToggleShowPassword -> {
                _state.value = _state.value.copy(
                    showPassword = !state.value.showPassword
                )
            }
            is PersonalDetailsEvent.ToggleShowNewPassword -> {
                _state.value = _state.value.copy(
                    showPassword = !state.value.showNewPassword
                )
            }
        }
    }

    fun setState(user: User) {
        _state.value = state.value.copy(
            user = user
        )

    }

}