package com.youppix.ecommercecourse.presentation.starting_app.auth

sealed class ValidateInput(val data: String? = null , message: String? = null) {

    class EmptyInput(data : String? = null , message: String?) : com.youppix.ecommercecourse.presentation.starting_app.auth.ValidateInput(data , message)

    class WrongFormatInput(data: String? = null , message: String?) : com.youppix.ecommercecourse.presentation.starting_app.auth.ValidateInput(data,message)

    class ValidInput(data: String?) : com.youppix.ecommercecourse.presentation.starting_app.auth.ValidateInput(data)

}