package com.youppix.ecommercecourse.data.remote.profile

import android.util.Log
import com.youppix.ecommercecourse.common.Resource
import com.youppix.ecommercecourse.common.Urls.CHECK_EMAIL_AVAILABILITY_URL
import com.youppix.ecommercecourse.common.Urls.DELETE_ADDRESS_URL
import com.youppix.ecommercecourse.common.Urls.GET_ALL_ADDRESS_URL
import com.youppix.ecommercecourse.common.Urls.GET_COMMUNE_URL
import com.youppix.ecommercecourse.common.Urls.UPDATE_PERSONAL_DETAILS_URL
import com.youppix.ecommercecourse.common.Urls.UPDATE_PROFILE_IMG_URL
import com.youppix.ecommercecourse.common.Urls.UPSERT_ADDRESS_URL
import com.youppix.ecommercecourse.data.remote.auth.dto.AuthResponse
import com.youppix.ecommercecourse.data.remote.profile.dto.AddressResponse
import com.youppix.ecommercecourse.data.remote.profile.dto.CommuneResponse
import com.youppix.ecommercecourse.domain.model.address.Address
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.ClientRequestException
import io.ktor.client.plugins.ServerResponseException
import io.ktor.client.request.forms.formData
import io.ktor.client.request.forms.submitFormWithBinaryData
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.Headers
import io.ktor.http.HttpHeaders
import io.ktor.utils.io.errors.IOException
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.serialization.Serializable
import kotlinx.serialization.SerializationException
import java.io.File

class ProfileService(private val client: HttpClient) {

    suspend fun uploadImage(userId: Int, file: File): Flow<Resource<AuthResponse>> = flow {
        try {
            emit(Resource.Loading())

            val response = client.submitFormWithBinaryData(
                url = UPDATE_PROFILE_IMG_URL,
                formData = formData {
                    append("profileImg", file.readBytes(), Headers.build {
                        append(HttpHeaders.ContentType, "image/jpeg")
                        append(HttpHeaders.ContentDisposition, "filename=${file.name}")
                    })
                    append("userId", userId)
                }
            )
            val responseBody = response.body<AuthResponse>()
            if (responseBody.status == "failure") {
                emit(Resource.Error(responseBody.message))
            } else {
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


    suspend fun updatePersonalDetails(
        userId: Int,
        userCustomerId: String,
        name: String, email: String, phone: String,
        oldPassword: String, newPassword: String,
    ): Flow<Resource<AuthResponse>> = flow {
        try {
            emit(Resource.Loading())

            @Serializable
            data class Body(
                val userId: Int,
                val name: String,
                val email: String,
                val phone: String,
                val oldPassword: String,
                val newPassword: String,
                val userCustomerId : String
            )

            val response = client.post(UPDATE_PERSONAL_DETAILS_URL) {
                setBody(Body(
                    userId = userId,
                    name =name,
                    email = email,
                    phone = phone,
                    oldPassword =oldPassword,
                    newPassword=newPassword ,
                    userCustomerId = userCustomerId))
            }
            val responseBody = response.body<AuthResponse>()
            if (responseBody.status == "success") {
                emit(Resource.Successful(responseBody))
            } else {
                if (responseBody.status == "password")
                    emit(Resource.Error(message = responseBody.message, data = responseBody))
                else
                    emit(Resource.Error(responseBody.message))
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

    suspend fun checkEmailAvailability(userId: Int, email: String): Flow<Resource<AuthResponse>> =
        flow {

            try {
                emit(Resource.Loading())
                @Serializable
                data class Body(
                    val userId: Int,
                    val email: String,
                )

                val response = client.post(CHECK_EMAIL_AVAILABILITY_URL) {
                    setBody(Body(userId, email))
                }
                val responseBody = response.body<AuthResponse>()
                if (responseBody.status == "success") {
                    emit(Resource.Successful(responseBody))
                } else {
                    emit(Resource.Error(responseBody.message))
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


    suspend fun getAllAddress(userId: Int): Flow<Resource<AddressResponse>> = flow {

        try {
            emit(Resource.Loading())
            @Serializable
            data class Body(
                val userId: Int,
            )

            val response = client.post(GET_ALL_ADDRESS_URL) {
                setBody(Body(userId))
            }
            val responseBody = response.body<AddressResponse>()

            emit(Resource.Successful(responseBody))

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

    suspend fun upsertAddress(address: Address,userCustomerId:String ,isArabic : Boolean): Flow<Resource<AuthResponse>> = flow {
        try {
            emit(Resource.Loading())
            @Serializable
            data class Body(
                val user_id: Int,
                val user_customer_id : String,
                val address_id: Int,
                val address_name: String,
                val address_wilaya: Int,
                val wilaya_name : String ,
                val address_commune: Int,
                val commune_name : String ,
                val address_code_postal: String,
                val address_default: Int,
                val address_specific : String
            )
            val response = client.post(UPSERT_ADDRESS_URL) {
                setBody(Body(user_id =address.userId ,
                    user_customer_id = userCustomerId,
                    address_id=address.addressId,
                    address_name = address.addressName,
                    address_wilaya = address.addressWilaya!!.wilayaId,
                    wilaya_name = if (isArabic)address.addressWilaya.wilayaNameAr else address.addressWilaya.wilayaName ,
                    address_commune =address.addressCommune!!.communeId,
                    commune_name = if (isArabic) address.addressCommune.communeNameAr else address.addressCommune.communeName,
                    address_code_postal = address.addressCodePostal,
                    address_default=address.addressDefault,
                    address_specific = address.addressSpecific
                    ))
            }
            val responseBody = response.body<AuthResponse>()
            if (responseBody.status == "success") {
                emit(Resource.Successful(responseBody))
            } else {
                emit(Resource.Error(responseBody.message))
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

    fun deleteAddress(addressId: Int, userId: Int): Flow<Resource<AuthResponse>> = flow {

        try {
            emit(Resource.Loading())

            @Serializable
            data class Body(
                val addressId: Int,
                val userId: Int,
            )

            val response = client.post(DELETE_ADDRESS_URL) {
                setBody(Body(addressId, userId))
            }
            val responseBody = response.body<AuthResponse>()
            if (responseBody.status == "success") {
                emit(Resource.Successful(responseBody))
            } else {
                emit(Resource.Error(responseBody.message))
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


    fun getCommune(wilayaId: Int): Flow<Resource<CommuneResponse>> = flow {

        try {
            emit(Resource.Loading())

            @Serializable
            data class Body(
                val wilayaId: Int,
            )

            val response = client.post(GET_COMMUNE_URL) {
                setBody(Body(wilayaId))
            }
            val responseBody = response.body<CommuneResponse>()
            if (responseBody.status == "success") {
                emit(Resource.Successful(responseBody))
            } else {
                emit(Resource.Error(responseBody.message))
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