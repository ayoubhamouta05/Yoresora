package com.youppix.ecommercecourse.domain.repository.signUp

import android.content.Context
import com.youppix.ecommercecourse.common.Resource

interface SignUpRepository {

    fun checkUserName(userName : String , context: Context) : Resource<Boolean>
    fun checkEmail (email : String , context: Context) : Resource<Boolean>//Flow<Resource<Boolean>>
    fun checkPhone(phone : String , context : Context) : Resource<Boolean>
    fun checkPassword(password : String,context: Context) : Resource<Boolean>// Flow<Resource<Boolean>>

}