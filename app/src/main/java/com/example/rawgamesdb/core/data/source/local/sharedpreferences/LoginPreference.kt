package com.example.rawgamesdb.core.data.source.local.sharedpreferences

import android.content.Context
import android.util.Log

internal class LoginPreference(context:Context) {
    private val preferences = context.getSharedPreferences(PREFS_NAME,Context.MODE_PRIVATE)

    fun setLoginToken(token:String){
        val editor = preferences.edit()
        editor.putString(LOGIN_TOKEN,token)
        editor.apply()
    }

    fun getLoginToken():String?{
        Log.d("GETTING TOKEN", "getLoginToken: ")
        return preferences.getString(LOGIN_TOKEN,"")
    }

    fun clearLoginToken(){
        val editor = preferences.edit()
        editor.clear()
        editor.apply()
        Log.d("CLEAREDDDD", "clearLoginToken: TOKEN NOW ${preferences.getString(LOGIN_TOKEN,"")}")
    }

    companion object {
        private const val PREFS_NAME = "login_prefs"
        private const val LOGIN_TOKEN = "login_token"
    }
}