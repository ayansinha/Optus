package org.techm.optus.data.repository

import org.techm.optus.data.model.album.AlbumModel

/**
 * @interface{AlbumRepository}
 */
interface AlbumRepository {
  suspend fun getAlbumApi(id: Int) : List<AlbumModel>
}