package org.techm.optus.data.repository

import org.techm.optus.data.network.APIService

class UserRepository(private val apiService: APIService) {

    suspend fun getUsersApi() = apiService.getUsers()
}