package org.techm.optus.ui.factory.album

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import org.techm.optus.data.repository.AlbumRepository
import org.techm.optus.ui.viewmodel.album.AlbumViewModel

/**
 * @class{AlbumViewModelFactory} -> creates an instance of album view model
 */
@Suppress("UNCHECKED_CAST")
class AlbumViewModelFactory(private val albumRepository: AlbumRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return AlbumViewModel(albumRepository) as T
    }
}