package com.app.vova_task.data.remote


import com.app.vova_task.data.remote.dto.Welcome10
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query


interface RetrofitApiService {


    @GET("api")
    suspend fun listHits(
        @Query("key") key: String = "33764718-b1e9035dd1aebe7be717a8125",
        @Query("q") q: String,
        @Query("image_type") image_type: String = "photo",
        @Query("pretty") pretty: String = "true",

        ): Response<Welcome10>


    companion object {
        const val BASE_URL = "https://pixabay.com/"
    }

}
