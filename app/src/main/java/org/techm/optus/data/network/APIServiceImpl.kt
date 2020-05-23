package org.techm.optus.data.network

import org.techm.optus.data.model.album.AlbumModel
import org.techm.optus.data.model.user.UserModel

class APIServiceImpl: APIService {
    override suspend fun getUsers() = APIBuilder.apiService.getUsers()

    override suspend fun getAlbums(id: Int) = APIBuilder.apiService.getAlbums(id)
}