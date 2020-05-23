package org.techm.optus.data.repository

import org.techm.optus.data.network.APIService

class AlbumRepository(private val apiService: APIService) {

    suspend fun getAlbumApi(_id: Int) = apiService.getAlbums(_id)
}