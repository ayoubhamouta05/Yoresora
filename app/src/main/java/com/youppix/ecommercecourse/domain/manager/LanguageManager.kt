package com.youppix.ecommercecourse.domain.manager

interface LanguageManager {

    suspend fun saveLanguage(key : String , value : String)

    fun getLanguage(key: String , defaultValue : String) : String

}
