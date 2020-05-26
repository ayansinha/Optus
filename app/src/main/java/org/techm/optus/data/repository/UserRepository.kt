package org.techm.optus.data.repository

import org.techm.optus.data.model.user.UserModel

/**
 * @interface{UserRepository} -> repository for user
 */
interface UserRepository{
    suspend fun getUsers() :List<UserModel>
}