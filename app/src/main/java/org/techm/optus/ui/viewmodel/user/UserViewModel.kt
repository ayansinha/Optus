package org.techm.optus.ui.viewmodel.user

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import org.techm.optus.data.model.user.UserModel
import org.techm.optus.data.repository.UserRepository
import org.techm.optus.util.Result

class UserViewModel(private val userRepo: UserRepository): ViewModel() {
    private val usersList = MutableLiveData<List<UserModel>>()

    init {
        getUsersList()
    }

    private fun getUsersList(){
        viewModelScope.launch {
            usersList.value = userRepo.getUsersApi()
        }
    }

    fun getUserList(): LiveData<List<UserModel>> {
        return usersList
    }
}