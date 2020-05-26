package org.techm.optus.ui.factory.user

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import org.techm.optus.data.repository.UserRepository
import org.techm.optus.ui.viewmodel.user.UserViewModel

/**
 * @class{UserViewModelFactory}
 */
@Suppress("UNCHECKED_CAST")
class UserViewModelFactory(private val userRepository: UserRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return UserViewModel(userRepository) as T
    }
}
