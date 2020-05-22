package org.techm.optus.data.network

import com.google.gson.Gson
import org.techm.optus.util.Constants.Companion.BASE_URL
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object APIBuilder {

    private fun getNetworkInstance(): Retrofit {

        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    val apiService: APIService = getNetworkInstance().create(APIService::class.java)
}