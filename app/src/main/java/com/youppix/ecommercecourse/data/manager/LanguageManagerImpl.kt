package com.youppix.ecommercecourse.data.manager

import android.content.Context
import android.content.SharedPreferences
import com.youppix.ecommercecourse.common.Constant.APP_LANG
import com.youppix.ecommercecourse.domain.manager.LanguageManager

class LanguageManagerImpl(
    private val context: Context
) : LanguageManager {

    private val languagePref: SharedPreferences =
        context.getSharedPreferences(APP_LANG, Context.MODE_PRIVATE)
    override suspend fun saveLanguage(key : String , value : String) {
        val editor = languagePref.edit()
        editor.putString(key , value)
        editor.apply()
    }

    override fun getLanguage(key : String ,defaultValue : String): String {
        return languagePref.getString(key , defaultValue)?:defaultValue
    }
}