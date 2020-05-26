package org.techm.optus.ui.viewmodel.album

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import org.techm.optus.data.model.album.AlbumModel
import org.techm.optus.data.repository.AlbumRepository
import org.techm.optus.util.Constants.Companion.ERROR_MSG
import org.techm.optus.util.Result

/**
 * @class{AlbumViewModel}
 */
class AlbumViewModel(private val albumRepo: AlbumRepository): ViewModel() {

    private val albumList = MutableLiveData<Result<List<AlbumModel>>>()

    /**
     * fetch list of albums
     */
    fun getAlbumList(id: Int): LiveData<Result<List<AlbumModel>>>{
        return getAlbum(id)
    }

    private fun getAlbum(id: Int):  LiveData<Result<List<AlbumModel>>>  {
        viewModelScope.launch {
            albumList.postValue(Result.loading(null))
            try {
                albumList.postValue(Result.success(albumRepo.getAlbumApi(id)))
            }catch (exception: Exception) {
                albumList.postValue(Result.error(ERROR_MSG , null))
                exception.message ?: "Error! $ERROR_MSG"
            }
        }
        return albumList
    }
}