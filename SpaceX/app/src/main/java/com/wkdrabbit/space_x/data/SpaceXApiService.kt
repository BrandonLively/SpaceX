package com.wkdrabbit.space_x.data

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.wkdrabbit.space_x.ui.models.Launch
import kotlinx.coroutines.Deferred
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface SpaceXApiService{

    @GET("launches/")
    fun getLaunchesAsync(): Deferred<List<Launch>>

    companion object{
        operator fun invoke(): SpaceXApiService{
            val okHttpClient = OkHttpClient.Builder()
                .build()
            return Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl("https://api.spacexdata.com/v3/")
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(SpaceXApiService::class.java)
        }
    }
}