package com.youppix.ecommercecourse.domain.useCases.home

import com.youppix.ecommercecourse.common.Resource
import com.youppix.ecommercecourse.data.remote.home.dto.HomeResponse
import com.youppix.ecommercecourse.domain.repository.home.HomeRepository
import kotlinx.coroutines.flow.Flow

class GetHomeDataUseCase(
    private val homeRepository: HomeRepository
) {
    suspend operator fun invoke(userId :Int): Flow<Resource<HomeResponse>> {
        return homeRepository.getHomeData(userId)
    }
}