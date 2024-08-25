package com.youppix.ecommercecourse.data.remote.details

import android.util.Log
import com.youppix.ecommercecourse.common.Resource
import com.youppix.ecommercecourse.common.Urls
import com.youppix.ecommercecourse.common.Urls.ADD_FAVORITE_URL
import com.youppix.ecommercecourse.common.Urls.CHECK_SIZE_EXISTENCE_URL
import com.youppix.ecommercecourse.common.Urls.ITEM_DETAILS_URL
import com.youppix.ecommercecourse.common.Urls.UPSERT_CUSTOM_SIZE_URL
import com.youppix.ecommercecourse.data.remote.auth.dto.AuthResponse
import com.youppix.ecommercecourse.data.remote.details.dto.DetailsResponse
import com.youppix.ecommercecourse.domain.model.details.Size
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

class DetailsService(private val client: HttpClient) {
    suspend fun getItemDetails(
        itemId: Int,
        userId: Int
    ): Flow<Resource<DetailsResponse>> =
        flow {
            try {
                emit(Resource.Loading())
                @Serializable
                data class Body(
                    val itemId: Int,
                    val userId: Int
                )

                val response = client.post(ITEM_DETAILS_URL) {
                    setBody(Body(itemId, userId))
                }


                val responseBody = response.body<DetailsResponse>()

                emit(Resource.Successful(responseBody))
                Log.d("SearchService", response.body())
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

    suspend fun addOrDeleteFromFavorite(userId: Int, itemId: Int): Flow<Resource<AuthResponse>> =
        flow {
            try {
                emit(Resource.Loading())
                @Serializable
                data class Body(
                    val userId: Int,
                    val itemId: Int
                )

                val response = client.post(ADD_FAVORITE_URL) {
                    setBody(Body(userId, itemId))
                }


                val responseBody = response.body<AuthResponse>()

                emit(Resource.Successful(responseBody))
                Log.d("SearchService", response.body())
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


    suspend fun checkSizeExistence(userId: Int): Flow<Resource<AuthResponse>> = flow {
        try {

            emit(Resource.Loading())
            @Serializable
            data class Body(
                val userId: Int
            )

            val response = client.post(CHECK_SIZE_EXISTENCE_URL) {
                setBody(Body(userId = userId))
            }
            val responseBody = response.body<AuthResponse>()

            if (responseBody.status == "failure") {
                emit(Resource.Error(responseBody.message))
            } else {
                emit(Resource.Successful(responseBody))
            }


            Log.d("SearchService", response.body())
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

    suspend fun upsertCustomSize(size: Size): Flow<Resource<AuthResponse>> = flow {
        try {
            emit(Resource.Loading())

            val response = client.post(UPSERT_CUSTOM_SIZE_URL) {
                setBody(size)
            }
            val responseBody = response.body<AuthResponse>()

            if (responseBody.status == "failure")
                emit(Resource.Error(responseBody.message))
            else
                emit(Resource.Successful(responseBody))

            Log.d("SearchService", response.body())
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

    suspend fun addOrDeleteCartItem(
        itemId: Int,
        userId: Int,
        itemSize: Int,
        itemColor: Int,
    ): Flow<Resource<AuthResponse>> = flow {
        try {

            emit(Resource.Loading())
            @Serializable
            data class Body(
                val userId: Int,
                val itemId: Int,
                val itemSize: Int,
                val itemColor: Int,
            )

            val response = client.post(Urls.ADD_CART_URL) {
                setBody(Body(userId, itemId, itemSize, itemColor))
            }
            val responseBody = response.body<AuthResponse>()

            emit(Resource.Successful(responseBody))
            Log.d("SignUpService", response.body())
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