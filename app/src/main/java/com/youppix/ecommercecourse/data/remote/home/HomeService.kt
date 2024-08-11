package com.youppix.ecommercecourse.data.remote.home

import android.util.Log
import com.youppix.ecommercecourse.common.Resource
import com.youppix.ecommercecourse.common.Urls.ALL_ITEMS_URL
import com.youppix.ecommercecourse.common.Urls.CATEGORIES_URL
import com.youppix.ecommercecourse.common.Urls.COlORS_URL
import com.youppix.ecommercecourse.common.Urls.HOME_URL
import com.youppix.ecommercecourse.common.Urls.ITEMS_BY_CATEGORY_URL
import com.youppix.ecommercecourse.common.Urls.ITEMS_BY_FILTERING_URL
import com.youppix.ecommercecourse.data.remote.home.dto.ColorResponse
import com.youppix.ecommercecourse.data.remote.home.dto.HomeResponse
import com.youppix.ecommercecourse.data.remote.home.dto.ItemsResponse
import com.youppix.ecommercecourse.domain.model.categories.CategoryData
import com.youppix.ecommercecourse.domain.model.items.ColorData
import com.youppix.ecommercecourse.domain.model.items.FilteringItems
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

class HomeService(private val client: HttpClient) {
    suspend fun getHomeData(): Flow<Resource<HomeResponse>> = flow {
        try {
            emit(Resource.Loading())

            val response = client.get(HOME_URL)
            val responseBody = response.body<HomeResponse>()

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
            Log.d("SignUpService", "Couldn't reach server: ${e.localizedMessage}")
        } catch (e: SerializationException) {
            emit(Resource.Error("Serialization error"))
            Log.d("SignUpService", "Serialization error: ${e.localizedMessage}")
        }
    }

    suspend fun getItemsByCategory(category: Int): Flow<Resource<ItemsResponse>> = flow {
        try {

            emit(Resource.Loading())
            @Serializable
            data class Data(
                val categories_id: Int
            )

            val response = client.post(ITEMS_BY_CATEGORY_URL) {
                setBody(Data(category))
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

    suspend fun getAllCategories(): Flow<Resource<List<CategoryData>>> = flow {
        try {
            emit(Resource.Loading())
            val response = client.get(CATEGORIES_URL)

            @Serializable
            data class Response(
                val data: List<CategoryData>?,
                val status: String,
                val message: String
            )

            val responseBody = response.body<Response>().data ?: emptyList()

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

    suspend fun getAllColors(): Flow<Resource<List<ColorData>>> = flow {
        try {
            emit(Resource.Loading())
            val response = client.get(COlORS_URL)


            val responseBody = response.body<ColorResponse>().data

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

    suspend fun getItemsByFiltering(filteringItems: FilteringItems): Flow<Resource<ItemsResponse>> =
        flow {
            Log.d("FilteringItems" , "filtering items  : $filteringItems")
            try {
                emit(Resource.Loading())
                @Serializable
                data class Body(
                    val itemsCat: Int?,
                    val initialPrice: Int ,
                    val finalPrice: Int ,
                    val itemsName: String ,
                    val initialDiscount: Int,
                    val finalDiscount: Int ,
                    val colors : List<Int>
                )
                val body = Body(
                    filteringItems.itemsCat,
                    filteringItems.initialPrice,
                    filteringItems.finalPrice,
                    filteringItems.itemsName,
                    filteringItems.initialDiscount,
                    filteringItems.finalDiscount,
                    filteringItems.colors.map { it.colors_id }
                )

                val response = client.post(ITEMS_BY_FILTERING_URL) {
                    setBody(body)
                }
                val responseBody = response.body<ItemsResponse>()

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
}