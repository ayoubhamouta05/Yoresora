package com.youppix.ecommercecourse.data.remote.auth

import com.youppix.ecommercecourse.common.Constant.BASE_URL
import com.youppix.ecommercecourse.common.Constant.SIGNUP_URL
import com.youppix.ecommercecourse.common.Resource
import com.youppix.ecommercecourse.data.remote.auth.dto.SignUpResponse
import com.youppix.ecommercecourse.domain.model.User
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST

interface SignUpApi {
    @Headers("Accept: application/json",
        "content-type: application/json")
    @POST(SIGNUP_URL)
    suspend fun addUser(@Body user: User) : SignUpResponse



}