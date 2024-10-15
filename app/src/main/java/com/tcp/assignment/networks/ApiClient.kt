package com.sama.health_life.network

import android.app.Activity
import com.google.gson.GsonBuilder
import kotlinx.coroutines.runBlocking
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object ApiClient {

    private const val URL = "http://demo1414406.mockable.io/"



    private var retrofit: Retrofit? = null
    private var apiInterface: ApiInterface? = null

    private fun getOkHttpClient(): Retrofit {
        val okHttpClient = OkHttpClient.Builder()
            .readTimeout(120, TimeUnit.SECONDS)
            .connectTimeout(120, TimeUnit.SECONDS)
            .writeTimeout(120, TimeUnit.SECONDS)
            .build()

        val gson = GsonBuilder().setLenient().create()

        retrofit = Retrofit.Builder()
            .baseUrl(URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(okHttpClient)
            .build()

        return retrofit!!
    }

    fun getApiInterface(): ApiInterface {
        apiInterface = getOkHttpClient().create(ApiInterface::class.java)
        return apiInterface!!
    }
}
