package com.krikorherlopian.fancycars.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.krikorherlopian.fancycars.db.CarDbSchema.CarTable

class CarBaseHelper(context: Context?) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, VERSION) {
    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(
            "create table " + CarTable.NAME.toString() + "(" +
                    CarTable.Cols.ID.toString() + ", " +
                    CarTable.Cols.IMG.toString() + ", " +
                    CarTable.Cols.CARNAME.toString() + ", " +
                    CarTable.Cols.MAKE.toString() + ", " +
                    CarTable.Cols.YEAR.toString() + ", " +
                    CarTable.Cols.AVAILABILITY.toString() + ", " +
                    CarTable.Cols.MODEL.toString() +
                    ")"
        )
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {

    }

    companion object {
        private val TAG = "CarBaseHelper"
        private val VERSION = 1
        private val DATABASE_NAME = "carBase.db"
    }
}