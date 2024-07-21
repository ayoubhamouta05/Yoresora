package com.youppix.ecommercecourse.data.remote.auth

import android.util.Log
import com.youppix.ecommercecourse.common.Constant.SIGNUP_URL
import com.youppix.ecommercecourse.common.Resource
import com.youppix.ecommercecourse.common.StatusResponse
import com.youppix.ecommercecourse.data.remote.auth.dto.SignUpResponse
import com.youppix.ecommercecourse.domain.model.User
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.ClientRequestException
import io.ktor.client.plugins.ServerResponseException
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.utils.io.errors.IOException
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.serialization.SerializationException

class SignUpService(private val client: HttpClient) {

    suspend fun addUser(user: User): Flow<Resource<SignUpResponse>> = flow {
        try {
            emit(Resource.Loading())
            val response = client.post(SIGNUP_URL) {
                    setBody(user)
            }
            val responseBody = response.body<SignUpResponse>()
            if (responseBody.status == StatusResponse.failure.name){
                emit(Resource.Error(responseBody.message))
            }else{
                emit(Resource.Successful(responseBody))
            }

            Log.d("SignUpService" , response.body())
        } catch (e: ClientRequestException) {
            emit(Resource.Error("Client request error: ${e.localizedMessage}"))
            Log.d("SignUpService" , "Client request error: ${e.localizedMessage}")
        } catch (e: ServerResponseException) {
            emit(Resource.Error("Server response error: ${e.localizedMessage}"))
            Log.d("SignUpService" , "Server response error: ${e.localizedMessage}")
        } catch (e: IOException) {
            emit(Resource.Error("Couldn't reach server: ${e.localizedMessage}"))
            Log.d("SignUpService" , "Couldn't reach server: ${e.localizedMessage}")
        } catch (e: SerializationException) {
            emit(Resource.Error("Serialization error: ${e.localizedMessage}"))
            Log.d("SignUpService" , "Serialization error: ${e.localizedMessage}")
        }
    }
}


