package org.techm.optus.data.model.user

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


/**
 * data model for user geolocation
 */
data class Geo(

    @SerializedName("lat")
    @Expose
    val lat: String,

    @SerializedName("lng")
    @Expose
    val lng: String
)