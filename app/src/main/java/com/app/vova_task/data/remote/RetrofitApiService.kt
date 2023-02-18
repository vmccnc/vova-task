package com.app.vova_task.data.remote


import com.app.vova_task.data.remote.dto.MachineDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path


interface RetrofitApiService {


    @GET("phone_search/{rr}")
    suspend fun listSawings(@Path("rr") rr: String ): Response<List<MachineDto>>


    companion object {
        const val BASE_URL = "http://q11.jvmhost.net/"
    }

}
