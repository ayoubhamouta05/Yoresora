package com.youppix.ecommercecourse.data.remote.auth

import android.util.Log
import androidx.compose.ui.res.stringResource
import com.youppix.ecommercecourse.common.Constant.CHECK_EMAIL_URL
import com.youppix.ecommercecourse.common.Constant.LOGIN_URL
import com.youppix.ecommercecourse.common.Constant.RESET_PASSWORD_URL
import com.youppix.ecommercecourse.common.Constant.SIGNUP_URL
import com.youppix.ecommercecourse.common.Constant.VERIFY_CODE_FORGOT_PASSWORD_URL
import com.youppix.ecommercecourse.common.Constant.VERIFY_CODE_URL
import com.youppix.ecommercecourse.common.Resource
import com.youppix.ecommercecourse.common.StatusResponse
import com.youppix.ecommercecourse.data.remote.auth.dto.AuthResponse
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
import kotlinx.serialization.Serializable
import kotlinx.serialization.SerializationException

class AuthService(private val client: HttpClient) {

    suspend fun addUser(user: User): Flow<Resource<AuthResponse>> = flow {
        try {
            emit(Resource.Loading())
            val response = client.post(SIGNUP_URL) {
                setBody(user)
            }
            val responseBody = response.body<AuthResponse>()
            if (responseBody.status == StatusResponse.failure.name) {
                emit(Resource.Error(responseBody.message))
            } else {
                emit(Resource.Successful(responseBody))
            }

            Log.d("SignUpService", response.body())
        } catch (e: ClientRequestException) {
            emit(Resource.Error("Client request error: ${e.localizedMessage}"))
            Log.d("SignUpService", "Client request error: ${e.localizedMessage}")
        } catch (e: ServerResponseException) {
            emit(Resource.Error("Server response error: ${e.localizedMessage}"))
            Log.d("SignUpService", "Server response error: ${e.localizedMessage}")
        } catch (e: IOException) {
            emit(Resource.Error("Couldn't reach server: ${e.localizedMessage}"))
            Log.d("SignUpService", "Couldn't reach server: ${e.localizedMessage}")
        } catch (e: SerializationException) {
            emit(Resource.Error("Serialization error: ${e.localizedMessage}"))
            Log.d("SignUpService", "Serialization error: ${e.localizedMessage}")
        }
    }

    suspend fun verifyCode(
        email: String,
        verifyCode: String,
        verifyCodeForgotPassword: Boolean = false
    ): Flow<Resource<AuthResponse>> = flow {
        try {
            @Serializable
            data class Data(
                val email: String,
                val verifycode: String
            )
            emit(Resource.Loading())
            val response = client.post(
                if (verifyCodeForgotPassword){
                    VERIFY_CODE_FORGOT_PASSWORD_URL
                }else {
                    VERIFY_CODE_URL
                }
            ) {
                setBody(
                    Data(
                        email = email,
                        verifycode = verifyCode
                    )
                )
            }
            val responseBody = response.body<AuthResponse>()
            if (responseBody.status == StatusResponse.failure.name) {
                emit(Resource.Error(responseBody.message))
            } else {
                emit(Resource.Successful(responseBody))
            }

            Log.d("SignUpService", response.body())
        } catch (e: ClientRequestException) {
            emit(Resource.Error("Client request error: ${e.localizedMessage}"))
            Log.d("SignUpService", "Client request error: ${e.localizedMessage}")
        } catch (e: ServerResponseException) {
            emit(Resource.Error("Server response error: ${e.localizedMessage}"))
            Log.d("SignUpService", "Server response error: ${e.localizedMessage}")
        } catch (e: IOException) {
            emit(Resource.Error("Couldn't reach server: ${e.localizedMessage}"))
            Log.d("SignUpService", "Couldn't reach server: ${e.localizedMessage}")
        } catch (e: SerializationException) {
            emit(Resource.Error("Serialization error: ${e.localizedMessage}"))
            Log.d("SignUpService", "Serialization error: ${e.localizedMessage}")
        }
    }

    suspend fun login(email: String, password: String): Flow<Resource<AuthResponse>> = flow {
        try {
            @Serializable
            data class Data(
                val email: String,
                val password: String,
            )
            emit(Resource.Loading())
            val response = client.post(LOGIN_URL) {
                setBody(
                    Data(
                        email = email,
                        password = password
                    )
                )
            }
            val responseBody = response.body<AuthResponse>()
            when (responseBody.status) {
                StatusResponse.failure.name -> {
                    emit(Resource.Error(responseBody.message))
                }

                StatusResponse.NeedApprove.name -> {
                    emit(Resource.Successful(responseBody, ""))
                }

                else -> {
                    emit(Resource.Successful(responseBody))
                }
            }
            Log.d("SignUpService", response.body())
        } catch (e: ClientRequestException) {
            emit(Resource.Error("Client request error: ${e.localizedMessage}"))
            Log.d("SignUpService", "Client request error: ${e.localizedMessage}")
        } catch (e: ServerResponseException) {
            emit(Resource.Error("Server response error: ${e.localizedMessage}"))
            Log.d("SignUpService", "Server response error: ${e.localizedMessage}")
        } catch (e: IOException) {
            emit(Resource.Error("Couldn't reach server: ${e.localizedMessage}"))
            Log.d("SignUpService", "Couldn't reach server: ${e.localizedMessage}")
        } catch (e: SerializationException) {
            emit(Resource.Error("Serialization error: ${e.localizedMessage}"))
            Log.d("SignUpService", "Serialization error: ${e.localizedMessage}")
        }
    }

    suspend fun checkEmail(email: String): Flow<Resource<AuthResponse>> = flow {
        try {
            @Serializable
            data class Data(
                val email: String,
            )
            emit(Resource.Loading())
            val response = client.post(CHECK_EMAIL_URL) {
                setBody(
                    Data(
                        email = email
                    )
                )
            }
            val responseBody = response.body<AuthResponse>()
            when (responseBody.status) {
                StatusResponse.failure.name -> {
                    emit(Resource.Error(responseBody.message))
                }

                else -> {
                    emit(Resource.Successful(responseBody))
                }
            }
            Log.d("SignUpService", response.body())
        } catch (e: ClientRequestException) {
            emit(Resource.Error("Client request error: ${e.localizedMessage}"))
            Log.d("SignUpService", "Client request error: ${e.localizedMessage}")
        } catch (e: ServerResponseException) {
            emit(Resource.Error("Server response error: ${e.localizedMessage}"))
            Log.d("SignUpService", "Server response error: ${e.localizedMessage}")
        } catch (e: IOException) {
            emit(Resource.Error("Couldn't reach server: ${e.localizedMessage}"))
            Log.d("SignUpService", "Couldn't reach server: ${e.localizedMessage}")
        } catch (e: SerializationException) {
            emit(Resource.Error("Serialization error: ${e.localizedMessage}"))
            Log.d("SignUpService", "Serialization error: ${e.localizedMessage}")
        }
    }

    suspend fun resetPassword(email : String , password: String): Flow<Resource<AuthResponse>> = flow {
        try {
            @Serializable
            data class Data(
                val email : String ,
                val password: String
            )
            emit(Resource.Loading())
            val response = client.post(RESET_PASSWORD_URL) {
                setBody(
                    Data(
                        email = email ,
                        password = password
                    )
                )
            }
            val responseBody = response.body<AuthResponse>()
            when (responseBody.status) {
                StatusResponse.failure.name -> {
                    emit(Resource.Error(responseBody.message))
                }
                else -> {
                    emit(Resource.Successful(responseBody))
                }
            }
            Log.d("SignUpService", response.body())
        } catch (e: ClientRequestException) {
            emit(Resource.Error("Client request error: ${e.localizedMessage}"))
            Log.d("SignUpService", "Client request error: ${e.localizedMessage}")
        } catch (e: ServerResponseException) {
            emit(Resource.Error("Server response error: ${e.localizedMessage}"))
            Log.d("SignUpService", "Server response error: ${e.localizedMessage}")
        } catch (e: IOException) {
            emit(Resource.Error("Couldn't reach server: ${e.localizedMessage}"))
            Log.d("SignUpService", "Couldn't reach server: ${e.localizedMessage}")
        } catch (e: SerializationException) {
            emit(Resource.Error("Serialization error: ${e.localizedMessage}"))
            Log.d("SignUpService", "Serialization error: ${e.localizedMessage}")
        }
    }

}


