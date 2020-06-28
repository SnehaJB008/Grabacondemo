package com.demo.grabacondemo.network

import com.demo.grabacondemo.modules.Photo
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface RestClient {

    @GET("photos/")
    fun getAlbum(): Call<ArrayList<Photo>?>?

    @GET("photos/{id}/")
    fun getPhotoById(@Path("id")photoId: Int): Call<Photo?>?
}