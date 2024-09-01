package com.youppix.ecommercecourse.domain.repository.address

import com.youppix.ecommercecourse.common.Resource
import com.youppix.ecommercecourse.data.remote.auth.dto.AuthResponse
import com.youppix.ecommercecourse.data.remote.profile.dto.AddressResponse
import com.youppix.ecommercecourse.data.remote.profile.dto.CommuneResponse
import com.youppix.ecommercecourse.domain.model.address.Address
import kotlinx.coroutines.flow.Flow

interface AddressRepository {
    suspend fun getAllAddress(userId: Int): Flow<Resource<AddressResponse>>
    suspend fun upsertAddress(address: Address): Flow<Resource<AuthResponse>>
    suspend fun deleteAddress(addressId : Int , userId: Int): Flow<Resource<AuthResponse>>
    suspend fun getCommune(wilayaId : Int) : Flow<Resource<CommuneResponse>>
}