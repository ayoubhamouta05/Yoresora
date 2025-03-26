package com.youppix.ecommercecourse.presentation.admin_home_app.users

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import cafe.adriel.voyager.core.model.ScreenModel
import com.youppix.ecommercecourse.domain.model.user.User
import javax.inject.Inject

class UsersViewModel @Inject constructor() : ScreenModel {

    private var _state = mutableStateOf(UserState())
    val state  : State<UserState> = _state


    init {
        _state.value = state.value.copy(
            users = listOf(
                User(1 ,
                    "fds" ,
                    "ayoub hamouta" ,
                    "ayoub@gmail.com" ,
                    "0660879814" ,
                    "" ,
                    0 ,
                    ),
            User(1 ,
                    "fds" ,
                    "ayoub hamouta" ,
                    "ayoub@gmail.com" ,
                    "0660879814" ,
                    "" ,
                    0 ,
                    ),
            User(1 ,
                    "fds" ,
                    "ayoub hamouta" ,
                    "ayoub@gmail.com" ,
                    "0660879814" ,
                    "" ,
                    0 ,
                    ),
            User(1 ,
                    "fds" ,
                    "ayoub hamouta" ,
                    "ayoub@gmail.com" ,
                    "0660879814" ,
                    "" ,
                    0 ,
                    ),
            User(1 ,
                    "fds" ,
                    "ayoub hamouta" ,
                    "ayoub@gmail.com" ,
                    "0660879814" ,
                    "" ,
                    0 ,
                    ),
            User(1 ,
                    "fds" ,
                    "ayoub hamouta" ,
                    "ayoub@gmail.com" ,
                    "0660879814" ,
                    "" ,
                    0 ,
                    ),
            User(1 ,
                    "fds" ,
                    "ayoub hamouta" ,
                    "ayoub@gmail.com" ,
                    "0660879814" ,
                    "" ,
                    0 ,
                    ),
            User(1 ,
                    "fds" ,
                    "ayoub hamouta" ,
                    "ayoub@gmail.com" ,
                    "0660879814" ,
                    "" ,
                    0 ,
                    ),
            User(1 ,
                    "fds" ,
                    "ayoub hamouta" ,
                    "ayoub@gmail.com" ,
                    "0660879814" ,
                    "" ,
                    0 ,
                    ),
            )
        )
    }


}