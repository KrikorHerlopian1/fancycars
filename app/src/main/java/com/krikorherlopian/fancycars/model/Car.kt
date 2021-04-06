package com.krikorherlopian.fancycars.model

import com.google.gson.annotations.SerializedName

data class Car(@SerializedName("id")
               val id: String?,
               @SerializedName("img")
               val img: String?,
               @SerializedName("name")
               val name: String?,
               @SerializedName("make")
               val make: String?,
               @SerializedName("model")
               val model: String?,
               @SerializedName("year")
               val year: String?,
               @SerializedName("availability")
               val availability: String?,
               @SerializedName("visualize")
               var visualizeAvailability: Boolean = false
               ){
                fun getAvailabilityInt(): Int{
                    if(availability?.toUpperCase().equals("IN DEALERSHIP")){
                        return 0
                    }
                    else if(availability?.toUpperCase().equals("OUT OF STOCK")){
                        return 2
                    }
                    else
                        return 1;//unavailable
                }
}
