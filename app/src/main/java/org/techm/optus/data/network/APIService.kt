package org.techm.optus.data.network

import org.techm.optus.data.model.album.AlbumModel
import org.techm.optus.data.model.user.UserModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface APIService {

    @GET("users")
    suspend fun getUsers(): Response<List<UserModel>>

    @GET("photos")
    suspend fun getAlbums(@Query("albumId") id: Int): Response<List<AlbumModel>>

}