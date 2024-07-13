package com.youppix.ecommercecourse.presentation.auth

sealed class ValidateInput(val data: String? = null , message: String? = null) {

    class EmptyInput(data : String? = null , message: String?) : ValidateInput(data , message)

    class WrongFormatInput(data: String? = null , message: String?) : ValidateInput(data,message)

    class ValidInput(data: String?) : ValidateInput(data)

}