package org.techm.optus.data.model.user

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


/**
 * data model for user company information
 */
data class Company(
    @SerializedName("bs")
    @Expose
    val bs: String,

    @SerializedName("catchPhrase")
    @Expose
    val catchPhrase: String,

    @SerializedName("name")
    @Expose
    val name: String
)