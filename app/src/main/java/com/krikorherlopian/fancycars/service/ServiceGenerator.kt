package com.krikorherlopian.fancycars.service


import com.krikorherlopian.fancycars.apiinterface.ApiInterface
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
object ServiceGenerator {
    private val retrofitBuilder = Retrofit.Builder()
        .baseUrl("http://fancycars.atwebpages.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
    private val retrofit = retrofitBuilder.build()
    val apiInterfaceApi = retrofit.create(ApiInterface::class.java)
}