package com.ad8.presentation.util

import android.content.SharedPreferences
import com.ad8.domain.util.Constants
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserHelper @Inject constructor(private val sharedPreferences: SharedPreferences) {

    fun saveToken(key: String?) {
        sharedPreferences.edit().putString(Constants.TOKEN, key).apply()
    }

    fun saveNotificationToken(key: String) {
        sharedPreferences.edit().putString(Constants.NOTIFICATION_TOKEN, key).apply()
    }

    fun getNotificationToken(): String {
        return sharedPreferences.getString(Constants.NOTIFICATION_TOKEN, "") ?: ""
    }


    fun saveUserId(id: Int?) {
        sharedPreferences.edit().putInt(Constants.USER_ID, id ?: 0).apply()
    }

    fun saveName(name: String?) {
        sharedPreferences.edit().putString(Constants.NAME, name).apply()
    }

    fun getName(): String? {
        return sharedPreferences.getString(Constants.NAME, null)
    }

    fun savePhone(name: String?) {
        sharedPreferences.edit().putString(Constants.PHONE, name).apply()
    }

    fun getPhone(): String? {
        return sharedPreferences.getString(Constants.PHONE, null)
    }

    fun saveEmail(name: String?) {
        sharedPreferences.edit().putString(Constants.EMAIL, name).apply()
    }

    fun getEmail(): String? {
        return sharedPreferences.getString(Constants.EMAIL, null)
    }

    fun saveGroup(name: String?) {
        sharedPreferences.edit().putString(Constants.GROUP, name).apply()
    }

    fun getGroup(): String? {
        return sharedPreferences.getString(Constants.GROUP, null)
    }


    fun saveRefreshToken(token: String?) {
        sharedPreferences.edit().putString(Constants.REFRESH_TOKEN, token).apply()
    }

    fun getRefreshToken(): String? {
        return sharedPreferences.getString(Constants.REFRESH_TOKEN, "")
    }


    fun getUserId(): Int? = if (sharedPreferences.getInt(Constants.USER_ID, 0) > 0) sharedPreferences.getInt(
        Constants.USER_ID,
        0
    ) else null

    fun getToken(): String {
        return sharedPreferences.getString(Constants.TOKEN, "") ?: ""
    }

    fun getLanguage(): String? {
        return sharedPreferences.getString(Constants.LANGUAGE, "")
    }

    fun saveLanguage(lang: String?) {
        sharedPreferences.edit().putString(Constants.LANGUAGE, lang).apply()
    }


    fun hasToken(): Boolean {
        return !sharedPreferences.getString(Constants.TOKEN, "").isNullOrEmpty()
    }

    fun resetData() {
        val lng = getLanguage()
        sharedPreferences.edit().clear().apply()
        saveLanguage(lng)
    }

    fun clearToken() {
        saveToken(null)
    }





}