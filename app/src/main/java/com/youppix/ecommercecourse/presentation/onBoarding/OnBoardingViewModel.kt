package com.youppix.ecommercecourse.presentation.onBoarding

import androidx.lifecycle.ViewModel
import com.youppix.ecommercecourse.domain.useCases.appEntry.AppEntryUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class OnBoardingViewModel @Inject constructor(
    private val appEntryUseCases: AppEntryUseCases
) : ViewModel() {

    fun saveAppEntry(key: String, value: String) {
        appEntryUseCases.saveAppEntryUseCase(key, value)
    }

}