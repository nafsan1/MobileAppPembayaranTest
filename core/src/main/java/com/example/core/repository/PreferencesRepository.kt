package com.example.core.repository

import android.content.SharedPreferences
import com.example.core.data.History
import com.example.core.util.Constants
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PreferencesRepository @Inject constructor(private val pref: SharedPreferences){
    private val editor = pref.edit()
    private val gson = Gson()

    fun getIsLogin() = Constants.ISLOGIN.getBooleanLogin()
    fun getToken() = Constants.TOKEN.getString()
    fun getSaldo() = Constants.SALDO.getInt()

    fun setIsLogin(login: Boolean) {
        Constants.ISLOGIN.put(login)
    }

    fun setToken(token: String) {
        Constants.TOKEN.put(token)
    }

    fun setSaldo(saldo:Int){
        Constants.SALDO.put(saldo)
    }

    fun saveList(list: MutableList<History>) {
        val editor = pref.edit()
        val json = gson.toJson(list)
        editor.putString(Constants.HISTORY_LIST, json)
        editor.apply()
    }

    fun getList(): MutableList<History>? {
        val json = pref.getString(Constants.HISTORY_LIST, null)
        val type = object : TypeToken<List<History>>() {}.type
        return gson.fromJson<MutableList<History>>(json, type)
    }

    private fun String.put(boolean: Boolean) {
        editor.putBoolean(this, boolean)
        editor.apply()
    }

    private fun String.put(data:String){
        editor.putString(this, data)
        editor.apply()
    }

    private fun String.put(data:Int){
        editor.putInt(this, data)
        editor.apply()
    }
    fun clearData() {
        editor.clear()
        editor.commit()
    }

    private fun String.getBooleanLogin() = pref.getBoolean(this, false)

    private fun String.getString() = pref.getString(this, "")
    private fun String.getInt() = pref.getInt(this, 0)

}