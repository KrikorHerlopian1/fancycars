package com.krikorherlopian.fancycars.model

data class CarRepoModel(var carList: MutableList<Car>?, var throwable: Throwable?) {
    constructor(carList: MutableList<Car>?) : this(carList, null)
    constructor(throwable: Throwable?) : this(null, throwable)
}
