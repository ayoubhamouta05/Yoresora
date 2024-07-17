package com.youppix.ecommercecourse.data.repository.signUp

import android.content.Context
import com.youppix.ecommercecourse.common.Constant
import com.youppix.ecommercecourse.common.Resource
import com.youppix.ecommercecourse.domain.repository.signUp.SignUpRepository

class SignUpRepositoryImpl : SignUpRepository {
    override fun checkUserName(userName: String, context: Context): Resource<Boolean> {
        return Constant.checkUserName(userName , context)
    }

    override fun checkEmail(email: String, context: Context): Resource<Boolean> {
        return Constant.checkEmail(email , context)
    }

    override fun checkPhone(phone: String, context: Context): Resource<Boolean> {
       return Constant.checkPhone(phone , context)
    }

    override fun checkPassword(password: String, context: Context): Resource<Boolean> {
        return Constant.checkPassword(password , context)
    }
}