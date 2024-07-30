package com.youppix.ecommercecourse.data.remote.home

import android.util.Log
import com.youppix.ecommercecourse.common.Resource
import com.youppix.ecommercecourse.common.Urls.ALL_ITEMS_URL
import com.youppix.ecommercecourse.common.Urls.HOME_URL
import com.youppix.ecommercecourse.common.Urls.ITEMS_BY_CATEGORY_URL
import com.youppix.ecommercecourse.data.remote.home.dto.HomeResponse
import com.youppix.ecommercecourse.data.remote.home.dto.ItemsResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.ClientRequestException
import io.ktor.client.plugins.ServerResponseException
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.utils.io.errors.IOException
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.serialization.Serializable
import kotlinx.serialization.SerializationException

class HomeService(private val client : HttpClient) {
    suspend fun getHomeData(): Flow<Resource<HomeResponse>> = flow {
        try {

            val response = client.get(HOME_URL)
            val responseBody = response.body<HomeResponse>()

            emit(Resource.Successful(responseBody))
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

    suspend fun getAllItems(): Flow<Resource<ItemsResponse>> = flow {
        try {

            val response = client.get(ALL_ITEMS_URL)
            val responseBody = response.body<ItemsResponse>()

            emit(Resource.Successful(responseBody))
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

    suspend fun getItemsByCategory(category : Int): Flow<Resource<ItemsResponse>> = flow {
        try {

            @Serializable
            data class Data(
                val categories_id : Int
            )
            val response = client.post(ITEMS_BY_CATEGORY_URL){
                setBody(Data(category))
            }
            val responseBody = response.body<ItemsResponse>()

            emit(Resource.Successful(responseBody))
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