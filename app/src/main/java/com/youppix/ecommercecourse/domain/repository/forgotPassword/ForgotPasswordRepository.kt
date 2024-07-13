package com.youppix.ecommercecourse.domain.repository.forgotPassword

import android.content.Context
import com.youppix.ecommercecourse.common.Resource

interface ForgotPasswordRepository {

    fun checkEmail(email : String , context: Context) : Resource<Boolean>

    fun checkPassword(password : String,context: Context ) : Resource<Boolean>


}