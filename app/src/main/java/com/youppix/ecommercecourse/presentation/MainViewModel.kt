package com.youppix.ecommercecourse.presentation

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.youppix.ecommercecourse.common.Constant.APP_ENTRY
import com.youppix.ecommercecourse.domain.useCases.appEntry.AppEntryUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel@Inject constructor(
    private val appEntryUseCases: AppEntryUseCases
) : ViewModel(){

    private var _appEntry = mutableStateOf(false)
    val appEntry : State<Boolean> =  _appEntry

    init {
        _appEntry.value = appEntryUseCases.getAppEntryUseCase(APP_ENTRY , _appEntry.value)
    }


}