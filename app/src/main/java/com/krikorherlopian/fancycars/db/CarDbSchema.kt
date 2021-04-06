package com.krikorherlopian.fancycars.db

class CarDbSchema {
    object CarTable {
        const val NAME = "car"
        object Cols {
            const val ID = "id"
            const val IMG = "img"
            const val CARNAME = "name"
            const val MAKE = "make"
            const val MODEL = "model"
            const val YEAR = "year"
            const val AVAILABILITY = "availability"
        }
    }
}