package com.krikorherlopian.fancycars.repository

import androidx.lifecycle.LiveData
import com.krikorherlopian.fancycars.apiinterface.ApiInterfaceClient
import com.krikorherlopian.fancycars.model.CarRepoModel

class Repository private constructor() {

    init {
        carsApiInterfaceClient = ApiInterfaceClient
    }
    companion object {
        private var carsApiInterfaceClient: ApiInterfaceClient? = ApiInterfaceClient

        fun getCar(): LiveData<CarRepoModel> {
            return carsApiInterfaceClient!!.cars
        }
        fun getCarCall() {
            carsApiInterfaceClient!!.getCarsCall()
        }
    }
}