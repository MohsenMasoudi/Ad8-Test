package com.ad8.app.di.module

import android.app.Application
import android.content.SharedPreferences
import android.util.Log
import com.facebook.stetho.okhttp3.StethoInterceptor
import com.ad8.app.BuildConfig
import com.ad8.data.BuildConfig.DEBUG
import com.ad8.domain.util.Constants
import com.ad8.domain.util.Constants.ACCESS_TOKEN
import com.ad8.presentation.util.extentions.loadFromSp
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import java.util.concurrent.TimeUnit
import javax.inject.Inject
import javax.inject.Singleton

private const val TIMEOUT = 30L

@Singleton
class BaseHttpClient @Inject constructor(private val context: Application, private val sharedPreferences: SharedPreferences) {

    var okHttpClient: OkHttpClient
    private val clientBuilder = OkHttpClient.Builder()
        .addInterceptor(
            HttpLoggingInterceptor().apply {
                level =
                    if (DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
            }

        )
        .connectTimeout(TIMEOUT, TimeUnit.SECONDS)
        .readTimeout(TIMEOUT, TimeUnit.SECONDS)
        .writeTimeout(TIMEOUT, TimeUnit.SECONDS)
        .addInterceptor(headersInterceptor())

    init {
        if (BuildConfig.DEBUG)
            clientBuilder.addNetworkInterceptor(StethoInterceptor())

        okHttpClient = clientBuilder.build()
    }


    private fun headersInterceptor() = Interceptor { chain ->
//        val token = sharedPreferences.getString(Constants.TOKEN, "")
        val token = loadFromSp(ACCESS_TOKEN,"")
        token.let { Log.i(Constants.TAG, it) }
        chain.proceed(
            chain.request().newBuilder()
                .addHeader("Authorization", "Bearer $token")
                .build()
        )
    }

}