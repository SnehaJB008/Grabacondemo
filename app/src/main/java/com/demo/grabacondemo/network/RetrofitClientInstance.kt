package com.demo.grabacondemo.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitClientInstance {
    private var retrofit: Retrofit? = null

    private val BASE_URL = "https://jsonplaceholder.typicode.com/"

    fun getRetrofitInstance(): Retrofit? {
        retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        return retrofit
    }

    fun getRestClient(): RestClient? {
        return getRetrofitInstance()?.create(RestClient::class.java)
    }
}