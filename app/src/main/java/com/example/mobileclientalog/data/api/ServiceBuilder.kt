package com.example.mobileclientalog.data.api

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object ServiceBuilder {

    // the Base adresse
    private const val BASE_URL = "https://8d0944c8b36d.ngrok.io/v1"

    val interceptor = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)

    //Create Okhttp Client
    private val okHttp: OkHttpClient = OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .readTimeout(5, TimeUnit.MINUTES)
            .writeTimeout(5, TimeUnit.MINUTES)
            .build()

    // Create Retrofit Builder
    private val builder : Retrofit.Builder = Retrofit.Builder()
            .client(okHttp)
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())

    //create Retrofit Instance
    private val retrofit = builder.build()


    fun <T> buildService(serviceProvider : Class<T>) :T {
        return retrofit.create(serviceProvider)
    }
}