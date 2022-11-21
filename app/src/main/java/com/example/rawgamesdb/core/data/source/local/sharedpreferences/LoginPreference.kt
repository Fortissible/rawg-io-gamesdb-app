package com.example.rawgamesdb.core.data.source.local.sharedpreferences

import android.content.Context

internal class LoginPreference(context:Context) {
    private val preferences = context.getSharedPreferences(PREFS_NAME,Context.MODE_PRIVATE)

    fun setLoginToken(token:String){
        val editor = preferences.edit()
        editor.putString(LOGIN_TOKEN,token)
        editor.apply()
    }

    fun getLoginToken():String?{
        return preferences.getString(LOGIN_TOKEN,"")
    }

    fun clearLoginToken():Boolean{
        val editor = preferences.edit()
        editor.clear()
        editor.apply()
        return true
    }

    companion object {
        private const val PREFS_NAME = "login_prefs"
        private const val LOGIN_TOKEN = "login_token"
    }
}