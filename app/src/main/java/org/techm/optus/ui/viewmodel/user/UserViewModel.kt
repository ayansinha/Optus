package org.techm.optus.ui.viewmodel.user

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import org.techm.optus.data.model.user.UserModel
import org.techm.optus.data.repository.UserRepository
import org.techm.optus.util.Constants.Companion.ERROR_MSG
import org.techm.optus.util.Result
import retrofit2.Response

class UserViewModel(private val userRepo: UserRepository): ViewModel() {
    private val usersList = MutableLiveData<Result<Response<List<UserModel>>>>()

    init {
        getUsersList()
    }

    private fun getUsersList(){
        viewModelScope.launch {
            usersList.postValue(Result.loading(null))
            try {
                usersList.postValue(Result.success(userRepo.getUsersApi()))
            }catch (exception: Exception) {
                usersList.postValue(Result.error(ERROR_MSG , null) )
                exception.message ?: "Error! $ERROR_MSG"
            }
        }
    }

    fun getUserList(): LiveData<Result<Response<List<UserModel>>>> {
        return usersList
    }
}