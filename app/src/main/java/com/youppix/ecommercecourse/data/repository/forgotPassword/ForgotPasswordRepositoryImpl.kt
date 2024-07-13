package com.youppix.ecommercecourse.data.repository.forgotPassword

import android.content.Context
import com.youppix.ecommercecourse.common.Constant
import com.youppix.ecommercecourse.common.Resource
import com.youppix.ecommercecourse.domain.repository.forgotPassword.ForgotPasswordRepository

class ForgotPasswordRepositoryImpl: ForgotPasswordRepository {
    override fun checkEmail(email: String , context: Context): Resource<Boolean> {
        return Constant.checkEmail(email ,context)
    }

    override fun checkPassword(password: String , context: Context): Resource<Boolean> {
        return Constant.checkPassword(password, context)
    }
}