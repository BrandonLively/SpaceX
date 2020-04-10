package com.wkdrabbit.space_x.data

import com.wkdrabbit.space_x.ui.models.Launch
import io.reactivex.Observable
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface SpaceXApiService{

    @GET("launches/")
    fun getLaunches(): Observable<List<Launch>>

    companion object Factory{
        fun create(): SpaceXApiService{
            val retrofit = Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("https://api.spacexdata.com/v3/")
                .build()

            return retrofit.create(SpaceXApiService::class.java)
        }
    }
}