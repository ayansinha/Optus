package org.techm.optus.data.model.album

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


/**
 * data model for album
 */
data class AlbumModel(
    @SerializedName("albumId")
    @Expose
    val albumId: Int,

    @SerializedName("id")
    @Expose
    val id: Int,

    @SerializedName("thumbnailUrl")
    @Expose
    val thumbnailUrl: String,

    @SerializedName("title")
    @Expose
    val title: String,

    @SerializedName("url")
    @Expose
    val url: String
)