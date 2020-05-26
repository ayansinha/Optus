package org.techm.optus.data.network

import org.techm.optus.data.model.album.AlbumModel
import org.techm.optus.data.model.user.UserModel
import org.techm.optus.data.repository.AlbumRepository
import org.techm.optus.data.repository.UserRepository


/**
 * @class{APIServiceImpl}
 */
class APIServiceImpl(private val apiService: APIService): UserRepository , AlbumRepository {

    override suspend fun getUsers(): List<UserModel> = apiService.getUsers()

    override suspend fun getAlbumApi(id: Int): List<AlbumModel> = apiService.getAlbums(id)

}