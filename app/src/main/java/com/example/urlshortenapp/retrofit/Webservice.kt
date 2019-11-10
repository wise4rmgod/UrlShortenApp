package com.example.urlshortenapp.retrofit

import com.example.urlshortenapp.model.PostUrl
import com.google.gson.GsonBuilder
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*

interface Webservice {

    @POST("api/links/")
    fun geturl(@Body post: PostUrl): Call<PostUrl>

    @GET("api/{v3}/{top-news}")
    suspend fun getNews(@Path("v3") v3: String, @Path("top-news") topnews: String, @Query("token") token: String): Call<ArticlesItem?>?

    companion object Factory {
        fun create(): Webservice {
            val retrofit = Retrofit.Builder()
                .baseUrl("https://rel.ink/")
                .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
                .build()
            return retrofit.create(Webservice::class.java)
        }
    }

}