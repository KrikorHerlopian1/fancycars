package com.krikorherlopian.fancycars.viewmodel

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.krikorherlopian.fancycars.model.Car
import com.krikorherlopian.fancycars.model.CarRepoModel
import com.krikorherlopian.fancycars.repository.Repository
import com.krikorherlopian.fancycars.db.CarBaseHelper
import com.krikorherlopian.fancycars.db.CarCursorWrapper
import com.krikorherlopian.fancycars.db.CarDbSchema

class CarListViewModel: ViewModel() {
    companion object{
        private var mDatabase: SQLiteDatabase? = null
        fun getCar(): LiveData<CarRepoModel> {
            return Repository.getCar()
        }
        fun getCarCall() {
            Repository.getCarCall()
        }
        fun createDb(context: Context){
            mDatabase = CarBaseHelper(context).writableDatabase
        }

        fun getContentValues(car: Car): ContentValues{
            var values = ContentValues()
            values.put(CarDbSchema.CarTable.Cols.ID, car.id)
            values.put(CarDbSchema.CarTable.Cols.IMG, car.img)
            values.put(CarDbSchema.CarTable.Cols.CARNAME, car.name)
            values.put(CarDbSchema.CarTable.Cols.MAKE, car.make)
            values.put(CarDbSchema.CarTable.Cols.MODEL, car.model)
            values.put(CarDbSchema.CarTable.Cols.YEAR, car.year)
            values.put(CarDbSchema.CarTable.Cols.AVAILABILITY, car.availability)
            return values
        }

        fun writeCar(cars: MutableList<Car>){
            for(car in cars){
                var values = getContentValues(car)
                mDatabase?.insert(CarDbSchema.CarTable.NAME, null, values)
            }
        }
        fun deleteCars(): Boolean{
            var f  = mDatabase?.delete(CarDbSchema.CarTable.NAME,  null, null)
            f?.let { return true }
            return false
        }
        fun queryCars(): CarCursorWrapper{
            var cursor = mDatabase?.query(CarDbSchema.CarTable.NAME,null,null,null,null,null,null)
            return CarCursorWrapper(cursor)
        }
        fun readCars() : MutableList<Car>{
            var cars:MutableList<Car> = mutableListOf()
            var cursor = queryCars()

            try {
                cursor.moveToFirst()
                while(!cursor.isAfterLast){
                    cars.add(cursor.car)
                    cursor.moveToNext()
                }
            } finally {
                cursor.close()
            }
            return cars
        }


        fun sortByCarName(cars: MutableList<Car>){
            cars.sortBy { it.name }
        }

        fun sortByCarAvailability(cars: MutableList<Car>) {
            cars.sortBy { it.getAvailabilityInt() }
        }
        fun getAvailability(car: Car){
            //api call to get availability. simualtion.
            car.visualizeAvailability = true
        }
    }
}