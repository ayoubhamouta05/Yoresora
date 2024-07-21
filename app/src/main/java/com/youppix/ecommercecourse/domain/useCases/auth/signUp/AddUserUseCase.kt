package com.youppix.ecommercecourse.domain.useCases.auth.signUp

import android.util.Log
import com.youppix.ecommercecourse.common.Resource
import com.youppix.ecommercecourse.common.Status
import com.youppix.ecommercecourse.data.remote.auth.dto.SignUpResponse
import com.youppix.ecommercecourse.domain.model.User
import com.youppix.ecommercecourse.domain.repository.signUp.SignUpRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class AddUserUseCase(
    private val signUpRepository: SignUpRepository
) {
    suspend operator fun invoke(user: User): Flow<Resource<SignUpResponse>> = flow {
        try {
            emit(Resource.Loading())
            val result =  signUpRepository.addUser(user)
//            Log.d("AddUserUserCase" ,"result = $result")
            if (result.status == Status.failure.name){
                emit(Resource.Error(result.message))
            }else{
                emit(Resource.Successful(result))
            }
            Log.d("AddUserUserCase" , result.status + "  " + result.message)

        }catch (e: Exception){
            emit(Resource.Error(e.localizedMessage ?: "An Unexpected Error Occurred"))
            Log.d("AddUserUserCase" , e.localizedMessage ?:"An Unexpected Error Occurred")
        }


    }
}