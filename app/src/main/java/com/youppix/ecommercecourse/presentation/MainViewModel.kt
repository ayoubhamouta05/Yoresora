package com.youppix.ecommercecourse.presentation

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.youppix.ecommercecourse.domain.useCases.appLanguage.LanguageManagerUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val languageManagerUseCases: LanguageManagerUseCases
) : ViewModel() {

    var language  = mutableStateOf("")
        private set
    fun getLanguage(key:String, defaultValue : String ){
       language.value = languageManagerUseCases.getAppLanguageUseCase(key,defaultValue)
    }

    fun saveLanguage(key : String , value: String) = viewModelScope.launch {
        languageManagerUseCases.saveAppLanguageUseCase(key,value)
        language.value = value
    }

}