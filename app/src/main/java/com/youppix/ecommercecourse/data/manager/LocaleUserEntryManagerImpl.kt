package com.youppix.ecommercecourse.data.manager

import android.content.Context
import android.content.SharedPreferences
import com.youppix.ecommercecourse.common.Constant.APP_ENTRY
import com.youppix.ecommercecourse.domain.manager.LocaleUserEntryManager

class LocaleUserEntryManagerImpl(
    private val context : Context
) : LocaleUserEntryManager {

    private val appPref: SharedPreferences =
        context.getSharedPreferences(APP_ENTRY, Context.MODE_PRIVATE)
    override fun saveAppEntry(key : String , value : String) {
        val editor = appPref.edit()
        editor.putString(key , value)
        editor.apply()

    }

    override fun readAppEntry(key : String ,defaultValue : String): String {
        return appPref.getString(key , defaultValue) ?: "1"
    }
}