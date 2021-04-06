package com.krikorherlopian.fancycars.db


import android.database.Cursor
import android.database.CursorWrapper
import com.krikorherlopian.fancycars.model.Car
import com.krikorherlopian.fancycars.db.CarDbSchema.CarTable;


class CarCursorWrapper(cursor: Cursor?) : CursorWrapper(cursor) {
    val car: Car
        get() {
            val id = getString(getColumnIndex(CarTable.Cols.ID))
            val img = getString(getColumnIndex(CarTable.Cols.IMG))
            val carName = getString(getColumnIndex(CarTable.Cols.CARNAME))
            val make = getString(getColumnIndex(CarTable.Cols.MAKE))
            val model = getString(getColumnIndex(CarTable.Cols.MODEL))
            val year = getString(getColumnIndex(CarTable.Cols.YEAR))
            val availability = getString(getColumnIndex(CarTable.Cols.AVAILABILITY))
            return Car(id,img,carName,make,model,year,availability,false)
        }

}