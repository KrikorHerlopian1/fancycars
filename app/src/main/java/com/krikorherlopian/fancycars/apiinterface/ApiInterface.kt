package com.krikorherlopian.fancycars.apiinterface

import com.krikorherlopian.fancycars.model.Car
import io.reactivex.rxjava3.core.Single
import retrofit2.Response
import retrofit2.http.GET

interface ApiInterface {
    @GET("fancycars.json")
    fun getCar(): Single<Response<MutableList<Car>>>
}