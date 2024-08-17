package com.youppix.ecommercecourse.data.remote.profile

import android.util.Log
import com.youppix.ecommercecourse.common.Resource
import com.youppix.ecommercecourse.common.Urls.UPDATE_PROFILE_IMG_URL
import com.youppix.ecommercecourse.data.remote.auth.dto.AuthResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.ClientRequestException
import io.ktor.client.plugins.ServerResponseException
import io.ktor.client.request.forms.formData
import io.ktor.client.request.forms.submitFormWithBinaryData
import io.ktor.http.Headers
import io.ktor.http.HttpHeaders
import io.ktor.utils.io.errors.IOException
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.serialization.SerializationException
import java.io.File

class ProfileService(private val client: HttpClient) {

    suspend fun uploadImage(userId : Int ,file: File): Flow<Resource<AuthResponse>> = flow {
        try {
            emit(Resource.Loading())

            val response = client.submitFormWithBinaryData(
                url = UPDATE_PROFILE_IMG_URL,
                formData = formData {
                    append("profileImg", file.readBytes(), Headers.build {
                        append(HttpHeaders.ContentType, "image/jpeg")
                        append(HttpHeaders.ContentDisposition, "filename=${file.name}")
                    })
                    append("userId" , userId )
                }
            )
            val responseBody = response.body<AuthResponse>()
            if (responseBody.status == "failure"){
                emit(Resource.Error(responseBody.message))
            }else{
                emit(Resource.Successful(responseBody))
            }
            Log.d("ProfileService", "response : $responseBody")

        } catch (e: ClientRequestException) {
            emit(Resource.Error("Client request error"))
            Log.d("SignUpService", "Client request error: ${e.localizedMessage}")
        } catch (e: ServerResponseException) {
            emit(Resource.Error("Server response error"))
            Log.d("SignUpService", "Server response error: ${e.localizedMessage}")
        } catch (e: IOException) {
            emit(Resource.Error("Couldn't reach server"))
            Log.d("SignUpService", "Couldn't reach server: ${e.message}")
        } catch (e: SerializationException) {
            emit(Resource.Error("Serialization error"))
            Log.d("SignUpService", "Serialization error: ${e.localizedMessage}")
        }

    }

}