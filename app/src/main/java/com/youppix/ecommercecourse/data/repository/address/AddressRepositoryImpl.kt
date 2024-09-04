package com.youppix.ecommercecourse.data.repository.address

import com.youppix.ecommercecourse.common.Resource
import com.youppix.ecommercecourse.data.remote.auth.dto.AuthResponse
import com.youppix.ecommercecourse.data.remote.profile.ProfileService
import com.youppix.ecommercecourse.data.remote.profile.dto.AddressResponse
import com.youppix.ecommercecourse.data.remote.profile.dto.CommuneResponse
import com.youppix.ecommercecourse.domain.model.address.Address
import com.youppix.ecommercecourse.domain.repository.address.AddressRepository
import kotlinx.coroutines.flow.Flow

class AddressRepositoryImpl(private val profileService: ProfileService): AddressRepository {
    override suspend fun getAllAddress(userId: Int): Flow<Resource<AddressResponse>> {
        return profileService.getAllAddress(userId)
    }

    override suspend fun upsertAddress(address: Address,userCustomerId : String , isArabic: Boolean): Flow<Resource<AuthResponse>> {
        return profileService.upsertAddress(address , userCustomerId , isArabic)
    }

    override suspend fun deleteAddress(addressId: Int, userId: Int): Flow<Resource<AuthResponse>> {
        return profileService.deleteAddress(addressId , userId)
    }

    override suspend fun getCommune(wilayaId: Int): Flow<Resource<CommuneResponse>> {
        return profileService.getCommune(wilayaId)
    }
}
