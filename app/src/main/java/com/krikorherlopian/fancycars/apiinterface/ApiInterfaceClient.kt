package com.krikorherlopian.fancycars.apiinterface

import androidx.lifecycle.MutableLiveData
import com.krikorherlopian.fancycars.model.Car
import com.krikorherlopian.fancycars.model.CarRepoModel
import com.krikorherlopian.fancycars.service.ServiceGenerator
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.SingleObserver
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers
import retrofit2.Response


object ApiInterfaceClient {
    var cars = MutableLiveData<CarRepoModel>()
    fun getCarsCall(){
        var call = ServiceGenerator.apiInterfaceApi.getCar()
        call.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : SingleObserver<Response<MutableList<Car>>> {
                override fun onSubscribe(d: Disposable?) {
                }

                override fun onSuccess(value: Response<MutableList<Car>>?) {
                    val result: MutableList<Car> = value?.body() as MutableList<Car>
                    val carRepoModel = CarRepoModel(result)
                    cars.postValue(carRepoModel)
                }

                override fun onError(e: Throwable?) {
                    val carRepoModel = CarRepoModel(e)
                    cars.postValue(carRepoModel)
                }
            });
    }
}

