package org.techm.optus.ui.viewmodel.album

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import org.techm.optus.data.model.album.AlbumModel
import org.techm.optus.data.repository.AlbumRepository

class AlbumViewModel(private val albumRepo: AlbumRepository): ViewModel() {

    private val albumList = MutableLiveData<List<AlbumModel>>()


    fun getAlbumList(id: Int): LiveData<List<AlbumModel>>{
        return getList(id)
    }

    private fun getList(id: Int):  LiveData<List<AlbumModel>>  {
        viewModelScope.launch {
            albumList.value = albumRepo.getAlbumApi(id)
        }
        return albumList
    }
}