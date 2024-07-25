package com.youppix.ecommercecourse.presentation.starting_app.selectLanguage

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.youppix.ecommercecourse.domain.useCases.appEntry.AppEntryUseCases
import com.youppix.ecommercecourse.domain.useCases.appLanguage.LanguageManagerUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SelectLanguageViewModel @Inject constructor(
    private val languageManagerUseCases: LanguageManagerUseCases
) : ViewModel() {

    var language  = mutableStateOf("")
        private set

    val appEntry = mutableStateOf(false)

    fun getLanguage(key:String, defaultValue : String ){
       language.value = languageManagerUseCases.getAppLanguageUseCase(key,defaultValue)
    }

    fun saveLanguage(key : String , value: String) = viewModelScope.launch {
        languageManagerUseCases.saveAppLanguageUseCase(key,value)
        language.value = value
    }

}