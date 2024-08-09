package com.youppix.ecommercecourse.data.remote.favorites

import android.util.Log
import com.youppix.ecommercecourse.common.Resource
import com.youppix.ecommercecourse.common.Urls
import com.youppix.ecommercecourse.data.remote.auth.dto.AuthResponse
import com.youppix.ecommercecourse.data.remote.home.HomeService
import com.youppix.ecommercecourse.data.remote.home.dto.ItemsResponse
import com.youppix.ecommercecourse.domain.model.categories.CategoryData
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

class FavoritesService (private val client: HttpClient) {

    suspend fun getAllFavorites(userId: Int ,categoryId : Int) : Flow<Resource<ItemsResponse>> = flow{
        try {

            emit(Resource.Loading())
            @Serializable
            data class Body(
                val categoryId: Int,
                val userId: Int
            )
            val response = client.post(Urls.ALL_FAVORITES_URL){
                setBody(Body(categoryId , userId = userId))
            }
            val responseBody = response.body<ItemsResponse>()

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

    suspend fun getAllCategories(): Flow<Resource<List<CategoryData>>> =
        HomeService(client).getAllCategories()


    suspend fun addOrDeleteFavorite(itemId : Int ,userId : Int): Flow<Resource<AuthResponse>> = flow {
        try {

            emit(Resource.Loading())
            @Serializable
            data class Body(
                val userId: Int,
                val itemId: Int
            )
            val response = client.post(Urls.ADD_FAVORITE_URL){
                setBody(Body(userId , itemId))
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