package org.techm.optus.data.network

import org.techm.optus.data.model.album.AlbumModel
import org.techm.optus.data.model.user.UserModel
import retrofit2.http.GET
import retrofit2.http.Path

interface APIService {

    @GET("users")
    suspend fun getUsers(): List<UserModel>

    @GET("photos/{id}")
    suspend fun getAlbums(@Path("id") id: Int): List<AlbumModel>

}