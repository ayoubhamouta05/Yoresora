package com.youppix.ecommercecourse.domain.manager

interface LocaleUserEntryManager {
    fun saveAppEntry(key : String , value : Boolean)

    fun readAppEntry(key : String , defaultValue : Boolean) : Boolean
}